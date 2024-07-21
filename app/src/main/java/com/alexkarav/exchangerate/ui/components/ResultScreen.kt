package com.alexkarav.exchangerate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alexkarav.exchangerate.presenter.ExchangeResultViewModel


@Composable
fun ResultScreen(initialCurrency: String, initialMoney: Double, convertedCurrencyName: String, convertedMoneyAmount: Double) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Результат конвертации: ", fontSize = 20.sp)
        ElevatedCard(onClick = { }, modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(20.dp)) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                Text("$initialMoney $initialCurrency =", fontSize = 18.sp)
                Text("$convertedMoneyAmount $convertedCurrencyName", fontSize = 24.sp, maxLines = 2)
            }
        }
    }
}