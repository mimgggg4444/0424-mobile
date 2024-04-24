package com.daelim.springtest.main.repository


import com.daelim.*
import com.daelim.springtest.main.api.model.dto.LottoDto
import org.springframework.stereotype.Repository

@Repository
class LottoRepository {
    private val lottoNumbers = mutableListOf<LottoDto>()

    fun save(lotto: LottoDto): Boolean {
        lottoNumbers.add(lotto)
        return true
    }

    fun findAll(): List<LottoDto> {
        return lottoNumbers
    }
}