package com.daelim.springtest.main.api.model.dto

data class LottoResultResponseDto(
    val index: Int,
    val winningNumbers: LottoResultRequestDto,
    val results: List<LottoResult>
)
