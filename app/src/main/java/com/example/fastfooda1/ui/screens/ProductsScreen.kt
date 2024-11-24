package com.example.fastfooda1.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.R
import com.example.fastfooda1.models.Product
import com.example.fastfooda1.sampleData.sampleProducts
import com.example.fastfooda1.ui.components.product.ProductsSection


@Composable
fun ProductHomeScreen() {
    if (isInPortraitMode()) {
        PortraitHomeScreen()
    } else {
        LandscapeHomeScreen()
    }
}

@Composable
fun isInPortraitMode(): Boolean {
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
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

@Composable
fun LandscapeHomeScreen() {
    Row(
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
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


@Preview(showSystemUi = true, name = "Portrait Preview")
@Composable
fun ProductHomeScreenPortraitPreview() {
    PortraitHomeScreen()
}

@Preview(showSystemUi = true, widthDp = 800, heightDp = 400, name = "Landscape Preview")
@Composable
fun ProductHomeScreenLandscapePreview() {
    LandscapeHomeScreen()
}