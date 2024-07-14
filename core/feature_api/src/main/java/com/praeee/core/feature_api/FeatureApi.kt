package com.praeee.core.feature_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.praeee.core.common.navigation_constants.CoinFeature

interface FeatureApi  {
    fun registerGraph(
        navController : NavHostController,
        navGraphBuilder : NavGraphBuilder
    )
}