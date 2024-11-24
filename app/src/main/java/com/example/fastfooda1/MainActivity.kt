package com.example.fastfooda1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fastfooda1.database.AppDataContainer
import com.example.fastfooda1.ui.screens.customer.CustomerListScreen
import com.example.fastfooda1.ui.screens.customer.EditCustomerScreen
import com.example.fastfooda1.ui.screens.customer.InsertCustomerScreen
import com.example.fastfooda1.ui.screens.product.EditProductScreen
import com.example.fastfooda1.ui.screens.product.InsertProductScreen
import com.example.fastfooda1.ui.screens.product.ProductListScreen
import com.example.fastfooda1.ui.theme.FastFoodA1Theme
import com.example.fastfooda1.ui.theme.Storefront
import com.example.fastfooda1.viewmodels.CustomersViewModel
import com.example.fastfooda1.viewmodels.CustomersViewModelFactory
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

    private val customersRepository by lazy {
        AppDataContainer(applicationContext).customersRepository
    }

    private val customersViewModel: CustomersViewModel by viewModels {
        CustomersViewModelFactory(customersRepository)
    }


//    private lateinit var cepController: CepController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://viacep.com.br/ws/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val service = retrofit.create(ViaCepService::class.java)
//
//        cepController = CepController(service)

        setContent {
            App(productsViewModel, customersViewModel)
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Products : Screen("products", "Products", Storefront)
    data object Customers : Screen("customers", "Customers", Icons.Default.Person)
    data object Sales : Screen("sales", "Sales", Icons.Default.ShoppingCart)
}

@Composable
fun App(
    productsViewModel: ProductsViewModel,
    customersViewModel: CustomersViewModel
) {
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
                    customersViewModel = customersViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    productsViewModel: ProductsViewModel,
    customersViewModel: CustomersViewModel,
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
            CustomerListScreen(
                viewModel = customersViewModel,
                onNavigateToInsertCustomer = { navController.navigate("add_customer") },
                onNavigateToEditCustomer = { customerId ->
                    navController.navigate("edit_customer/$customerId")
                }
            )
        }
        composable("add_customer") {
            InsertCustomerScreen(
                viewModel = customersViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "edit_customer/{customerId}",
            arguments = listOf(navArgument("customerId") { type = NavType.IntType })
        ) { backStackEntry ->
            val customerId = backStackEntry.arguments?.getInt("customerId")
            if (customerId != null) {
                EditCustomerScreen(
                    viewModel = customersViewModel,
                    customerId = customerId,
                    onBack = { navController.popBackStack() }
                )
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