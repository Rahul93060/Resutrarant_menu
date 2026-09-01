package com.example.resutrarant_menu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.resutrarant_menu.ui.theme.Resutrarant_menuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Resutrarant_menuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RestaurantMenuExplorer(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantMenuExplorerPreview() {
    Resutrarant_menuTheme {
        RestaurantMenuExplorer()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantMenuExplorer(modifier: Modifier = Modifier) {

    val menuItems = listOf(
        mapOf(
            "name" to "Pizza",
            "price" to "₹299",
            "category" to "Pizza",
            "image" to "🍕",
            "desc" to "Delicious cheese pizza with fresh toppings."
        ),
        mapOf(
            "name" to "Veg Burger",
            "price" to "₹199",
            "category" to "Burger",
            "image" to "🍔",
            "desc" to "Crispy veg patty with fresh lettuce and sauce."
        ),
        mapOf(
            "name" to "Pasta Alfredo",
            "price" to "₹249",
            "category" to "Pasta",
            "image" to "🍝",
            "desc" to "Creamy white sauce pasta with herbs."
        ),
        mapOf(
            "name" to "Chocolate Cake",
            "price" to "₹149",
            "category" to "Dessert",
            "image" to "🍰",
            "desc" to "Rich and moist chocolate cake."
        )
    )

    var selectedItem by remember { mutableStateOf(menuItems[0]) }

    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 110.dp,
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                Text(
                    text = selectedItem["image"]!!,
                    fontSize = 55.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = selectedItem["name"]!!,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = selectedItem["category"]!!,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = selectedItem["desc"]!!,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = selectedItem["price"]!!,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        // Add to cart
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add to Cart")
                }
            }
        }
    ) { contentPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {

            Text(
                text = "Restaurant Menu",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(menuItems) { item ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedItem = item
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = item["image"]!!,
                                fontSize = 45.sp
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = item["name"]!!,
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = item["category"]!!,
                                    color = MaterialTheme.colorScheme.primary
                                )

                                Text(
                                    text = item["price"]!!,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "View Details"
                            )
                        }
                    }
                }
            }
        }
    }
}