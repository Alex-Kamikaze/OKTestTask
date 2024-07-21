package com.alexkarav.exchangerate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onConvertButtonClick: (String, Int) -> Unit) {
    val currencies = listOf("USD", "JPY", "EUR", "CZK", "GBP", "PLN")
    var currencyInput by remember { mutableStateOf("") }
    var menuExpanded by remember { mutableStateOf(false) }
    var itemSelected by remember { mutableStateOf(currencies[0])}

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Укажите валюту и количество рублей для конвертации", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(value = currencyInput, onValueChange = { currencyInput = it} )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ExposedDropdownMenuBox(expanded = menuExpanded, onExpandedChange = { menuExpanded = !menuExpanded }) {
            TextField(
                value = itemSelected,
                onValueChange = {},
                modifier = Modifier.menuAnchor(),
                label = { Text("Выбор валюты") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = menuExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                currencies.forEach { selectedOption ->
                    DropdownMenuItem(
                        text = { Text(selectedOption) },
                        onClick = { itemSelected = selectedOption; menuExpanded = false }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Button(onClick = { onConvertButtonClick(itemSelected, currencyInput.toInt()) }, shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.elevatedButtonColors()) {
            Text("Конвертировать")
        }
    }
}