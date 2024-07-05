package com.praeee.jetpackcomposeapp.ui.feature_home_page



data class HomeNavEvent(
    val onNavigateBack: () -> Unit = {},
    val onNavigateToNews: () -> Unit = {},
)
