package com.example.fastfooda1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fastfooda1.controllers.CepController
import com.example.fastfooda1.database.AppDataContainer
import com.example.fastfooda1.models.Address
import com.example.fastfooda1.models.ViaCepService
import com.example.fastfooda1.ui.screens.product.EditProductScreen
import com.example.fastfooda1.ui.screens.product.InsertProductScreen
import com.example.fastfooda1.ui.screens.product.ProductListScreen
import com.example.fastfooda1.ui.theme.FastFoodA1Theme
import com.example.fastfooda1.ui.theme.Storefront
import com.example.fastfooda1.viewmodels.ProductsViewModel
import com.example.fastfooda1.viewmodels.ProductsViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val productsRepository by lazy {
        AppDataContainer(applicationContext).productsRepository
    }

    private val productsViewModel: ProductsViewModel by viewModels {
        ProductsViewModelFactory(productsRepository)
    }

    private lateinit var cepController: CepController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ViaCepService::class.java)
        cepController = CepController(service)

        setContent {
            App(productsViewModel, cepController)
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Products : Screen("products", "Products", Storefront)
    data object Customers : Screen("customers", "Customers", Icons.Default.Person)
    data object Sales : Screen("sales", "Sales", Icons.Default.ShoppingCart)
}

@Composable
fun App(productsViewModel: ProductsViewModel, cepController: CepController) {
    FastFoodA1Theme {
        Surface {
            val navController = rememberNavController()
            Scaffold(
                topBar = {
                    NavigationBar(navController)
                }
            ) { innerPadding ->
                NavigationHost(
                    navController = navController,
                    productsViewModel = productsViewModel,
                    modifier = Modifier.padding(innerPadding),
                    cepController = cepController
                )
            }
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    productsViewModel: ProductsViewModel,
    cepController: CepController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Products.route,
        modifier = modifier
    ) {
        composable(Screen.Products.route) {
            ProductListScreen(
                viewModel = productsViewModel,
                onNavigateToInsertProduct = { navController.navigate("add_product") },
                onNavigateToEditProduct = { productId ->
                    navController.navigate("edit_product/$productId")
                }
            )
        }
        composable("add_product") {
            InsertProductScreen(
                viewModel = productsViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "edit_product/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(
                    viewModel = productsViewModel,
                    productId = productId,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Screen.Customers.route) {
            var cep by remember { mutableStateOf("") }
            var address by remember { mutableStateOf<Address?>(null) }
            var errorMessage by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = cep,
                    onValueChange = { cep = it },
                    label = { Text("Digite o CEP") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    if (cep.isNotBlank()) {
                        cepController.getAddress(cep,
                            { address = it; errorMessage = "" },
                            { errorMessage = "CEP não localizado ou inválido" }
                        )
                    }
                }) {
                    Text("Buscar")
                }
                Spacer(modifier = Modifier.height(16.dp))
                address?.let {
                    Text("CEP: ${it.cep}")
                    Text("Logradouro: ${it.logradouro}")
                    Text("Bairro: ${it.bairro}")
                    Text("Cidade: ${it.localidade} - ${it.uf}")
                }
                if (errorMessage.isNotEmpty()) {
                    Text(errorMessage, color = Color.Red)
                }
            }
        }

        composable(Screen.Sales.route) {
            Text("Sales Screen")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar(navController: NavHostController) {
    val screens = listOf(Screen.Products, Screen.Customers, Screen.Sales)
    TopAppBar(
        title = { Text("Fast Food A1") },
        actions = {
            screens.forEach { screen ->
                IconButton(onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                }
            }
        }
    )
}