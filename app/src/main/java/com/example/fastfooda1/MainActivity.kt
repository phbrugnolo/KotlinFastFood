package com.example.fastfooda1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fastfooda1.ui.screens.ProductHomeScreen
import com.example.fastfooda1.ui.theme.FastFoodA1Theme
import com.example.fastfooda1.ui.theme.Storefront

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Products : Screen("products", "Products", Storefront)
    object Customers : Screen("customers", "Customers", Icons.Default.Person)
    object Sales : Screen("sales", "Sales", Icons.Default.ShoppingCart)
}

@Composable
fun App() {
    FastFoodA1Theme {
        Surface {
            val navController = rememberNavController()
            Scaffold(
                topBar = {
                    NavigationBar(navController)
                }
            ) { innerPadding ->
                NavigationHost(navController, Modifier.padding(innerPadding))
            }
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

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Products.route,
        modifier = modifier
    ) {
        composable(Screen.Products.route) {
            Text("Im get oppened")
        }
        composable(Screen.Customers.route) {
            CustomerScreen()
        }
        composable(Screen.Sales.route) {
            SalesScreen()
        }
    }
}

@Composable
fun CustomerScreen() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Customer Screen")
    }
}

@Composable
fun SalesScreen() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Sales Screen")
    }
}