package com.daelim.springtest.main.api.model.dto

data class LottoResultRequestDto(
    val numbers: List<Int>,
    val bonusNumber: Int
)
