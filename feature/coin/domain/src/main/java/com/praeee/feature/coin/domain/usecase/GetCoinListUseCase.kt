package com.praeee.feature.coin.domain.usecase

import com.praeee.core.common.UiEvent
import com.praeee.feature.coin.domain.model.Coin
import com.praeee.feature.coin.domain.repo.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    operator fun invoke() = flow<UiEvent<List<Coin>>> {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(coinRepository.getCoinList()))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}