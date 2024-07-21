package com.alexkarav.exchangerate.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alexkarav.exchangerate.presenter.ExchangeResultViewModel
import com.alexkarav.exchangerate.presenter.LoadingState
import com.alexkarav.exchangerate.ui.components.HomeScreen
import com.alexkarav.exchangerate.ui.components.ResultScreen
import com.alexkarav.exchangerate.ui.components.ResultScreenArgs
import com.alexkarav.exchangerate.ui.theme.ExchangeRateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExchangeRateTheme {
                val navController = rememberNavController()
                val viewModel: ExchangeResultViewModel by viewModels()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<HomeScreen> {
                            HomeScreen { currency, amount ->
                                navController.navigate(ResultScreenArgs(currencyToConvert = currency, initialCurrencyValue = amount))
                            }
                        }

                        composable<ResultScreenArgs> {
                            val args = it.toRoute<ResultScreenArgs>()
                            viewModel.loadCurrencyRate(args.currencyToConvert)
                            val loadingState by viewModel.loadingState.collectAsState()
                            when(loadingState) {
                                is LoadingState.Error -> Toast.makeText(LocalContext.current, "Произошла ошибка при загрузке данных", Toast.LENGTH_LONG).show()
                                LoadingState.Idle -> {}
                                LoadingState.Loading -> {
                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                        CircularProgressIndicator(modifier = Modifier.width(64.dp))
                                    }
                                }
                                is LoadingState.Success -> {
                                    val convertionResult = viewModel.loadingState.value as LoadingState.Success
                                    val amountToPass = convertionResult.result?.convertionResult?.times(
                                        args.initialCurrencyValue
                                    )
                                    if (amountToPass != null) {
                                        ResultScreen(initialCurrency = "RUB", initialMoney = args.initialCurrencyValue.toDouble(), convertedCurrencyName = args.currencyToConvert, convertedMoneyAmount = amountToPass)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}