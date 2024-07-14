package com.praeee.feature.coin.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.praeee.core.feature_api.FeatureApi

interface CoinApi : FeatureApi {



}

class CoinApiImpl : CoinApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalCoinFeatureApi.registerGraph(
            navController, navGraphBuilder
        )
    }

}