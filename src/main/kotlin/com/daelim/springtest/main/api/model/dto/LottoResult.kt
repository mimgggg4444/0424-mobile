package com.daelim.springtest.main.api.model.dto

data class LottoResult(
    val numbers: List<Int>,
    val correctNumbers: Set<Int>,
    val result: String
)
