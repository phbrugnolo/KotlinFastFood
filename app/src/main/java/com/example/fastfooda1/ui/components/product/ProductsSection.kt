package com.example.fastfooda1.ui.components.product

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fastfooda1.R
import com.example.fastfooda1.models.Product

@Composable
fun ProductsSection(
    title: String, products: List<Product>
) {
    Column {
        Text(
            text = title, Modifier.padding(
                start = 16.dp, end = 16.dp
            ), fontSize = 20.sp, fontWeight = FontWeight(400)
        )
        Row(
            Modifier
                .padding(
                    top = 8.dp
                )
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier)
            for (p in products) {
                ProductItem(product = p)
            }
            Spacer(Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsSectionPreview() {
    ProductsSection(
        "Promoções", products = listOf(
            Product(
                name = LoremIpsum(50).values.first(),
                price = 14.99,
                image = R.drawable.ic_launcher_background,
                quantity = 50
            )
        )
    )
}