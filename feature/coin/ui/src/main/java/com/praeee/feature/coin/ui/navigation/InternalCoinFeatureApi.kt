package com.praeee.feature.coin.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.praeee.core.common.navigation_constants.CoinFeature
import com.praeee.core.feature_api.FeatureApi
import com.praeee.feature.coin.ui.screen.CoinScreen
import com.praeee.feature.coin.ui.screen.CoinViewModel

internal object InternalCoinFeatureApi : FeatureApi {
    override fun registerGraph(
        navController : NavHostController,
        navGraphBuilder : NavGraphBuilder
    ) {
//        navGraphBuilder.navigation(
//            startDestination = CoinFeature.coinScreenRoute,
//            route = CoinFeature.nestedRoute
//        ) {
//            composable(CoinFeature.coinScreenRoute) {
//                val viewModel = hiltViewModel<CoinViewModel>()
//                CoinScreen(
//                    viewModel = viewModel
//                )
//
//            }
//        }
    }
}