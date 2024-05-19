package com.praeee.jetpackcomposeapp.ui.repository

import android.util.Log
import com.praeee.jetpackcomposeapp.data.datasource.CoinDataSource
import com.praeee.jetpackcomposeapp.data.entity.coin_detail_response.CoinDetailResponse
import com.praeee.jetpackcomposeapp.data.entity.coin_list_response.CoinResponse
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.viewmodel.CoinViewModel
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val coinDataSource: CoinDataSource
) {

    suspend fun getCoinList() : Flow<ResourceState<CoinResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = coinDataSource.getCoinList()

            when  {
                response.isSuccessful && response.body() != null -> {
                    emit(ResourceState.Success(response.body()!!))
                }
                else -> {
                    emit(ResourceState.Error("Error fetching coin data"))
                }
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "Some error in flow"))
        }
    }

    suspend fun getCoinDetail(uuid: String) : Flow<ResourceState<CoinDetailResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = coinDataSource.getCoinDetail(uuid)

            when  {
                response.isSuccessful -> {
                    if (response.body()!!.status == "success") {
                        emit(ResourceState.Success(response.body()!!))
                    } else {
                        emit(ResourceState.Error("Error data status un success"))
                    }
                }
                else -> {
                    emit(ResourceState.Error("Error fetching coin data"))
                }
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "Some error in flow"))
        }
    }

    suspend fun getCoinSearch(search: String) : Flow<ResourceState<CoinResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = coinDataSource.getCoinSearch(search)

            when  {
                response.isSuccessful -> {
                    if (response.body()!!.status == "success") {
                        emit(ResourceState.Success(response.body()!!))
                    } else {
                        emit(ResourceState.Error("Error data status un success"))
                    }
                }
                else -> {
                    emit(ResourceState.Error("Error fetching coin data"))
                }
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "Some error in flow"))
        }
    }



}