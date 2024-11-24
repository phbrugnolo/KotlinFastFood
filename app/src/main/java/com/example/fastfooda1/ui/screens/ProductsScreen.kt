package com.example.fastfooda1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fastfooda1.R
import com.example.fastfooda1.models.Product
import com.example.fastfooda1.sampleData.sampleProducts
import com.example.fastfooda1.ui.components.product.ProductItem
import com.example.fastfooda1.ui.components.product.ProductsSection
import com.example.fastfooda1.viewmodels.ProductViewModel


@Composable
fun ProductHomeScreen() {
    PortraitHomeScreen()
}


@Composable
fun PortraitHomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier)
        ProductsSection("Promoções", sampleProducts)
        ProductsSection(
            "Doces", listOf(
                Product(
                    name = "Chocolate",
                    price = 5.99,
                    image = R.drawable.ic_launcher_background,
                    quantity = 5
                )
            )
        )
        ProductsSection("Bebidas", sampleProducts)
        Spacer(Modifier)
    }
}

//@Composable
//fun LandscapeHomeScreen() {
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(minSize = 200.dp),
//        contentPadding = PaddingValues(16.dp),
//        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        item(span = { GridItemSpan(maxLineSpan) }) {
//            Text(
//                text = "Promoções",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//        }
//        items(sampleProducts) { product ->
//            ProductItem(product = product)
//        }
//
//        item(span = { GridItemSpan(maxLineSpan) }) {
//            Text(
//                text = "Doces",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//        }
//        items(
//            listOf(
//                Product(
//                    name = "Chocolate",
//                    price = 5.99,
//                    image = R.drawable.ic_launcher_background,
//                    quantity = 5
//                )
//            )
//        ) { product ->
//            ProductItem(product = product)
//        }
//
//        item(span = { GridItemSpan(maxLineSpan) }) {
//            Text(
//                text = "Bebidas",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//        }
//        items(sampleProducts) { product ->
//            ProductItem(product = product)
//        }
//    }
//}

@Preview(showSystemUi = true)
@Composable
fun ProductHomeScreenPreview() {
    ProductHomeScreen()
}