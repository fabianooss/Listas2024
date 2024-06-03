package com.senac.listas2024

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senac.listas2024.model.Produto
import com.senac.listas2024.ui.theme.Listas2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Listas2024Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {

    val list = listOf(
        Produto(1, "Caneta", 10.0),
        Produto(2, "Borracha", 5.0),
        Produto(3, "Caderno", 8.5),
    )
    val ctx = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(ctx, "Novo",
                    Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription ="" )
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

             LazyColumn() {
                item {
                    Text(text = "Line 1")
                }
                item {
                    Text(text = "Line 2")
                }
                items(5) {
                    Text(text = "Line ${it}")
                }
                items(list) {
                    ProdutoCard(it)
                }
            }

        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProdutoCard(p: Produto) {
    val ctx = LocalContext.current
    Card(elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
          ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    Toast
                        .makeText(
                            ctx,
                            "Produto: ${p.nome}",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                },
                onLongClick = {
                    Toast
                        .makeText(
                            ctx,
                            "Long click Produto: ${p.nome}",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            )
    ) {

        Column(modifier = Modifier.padding(4.dp) ) {
            Text(text = p.nome,
                style = MaterialTheme.typography.titleLarge)
            Text(text = "R$ ${p.preco}",
                style = MaterialTheme.typography.bodySmall)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Listas2024Theme {
        MyApp()
    }
}