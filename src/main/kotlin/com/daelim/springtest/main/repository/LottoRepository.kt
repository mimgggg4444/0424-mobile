package com.daelim.springtest.main.repository


import com.daelim.*
import com.daelim.springtest.main.api.model.dto.LottoDto
import org.springframework.stereotype.Repository

@Repository
class LottoRepository {
    // 메모리에 로또 번호를 저장하는 리포지토리 (실제로는 데이터베이스를 사용해야 함)
    private val lottoNumbers = mutableListOf<LottoDto>()

    fun save(lotto: LottoDto): Boolean {
        // 로또 번호 저장 메서드
        lottoNumbers.add(lotto)
        return true
    }

    fun findAll(): List<LottoDto> {
        // 모든 로또 번호 조회 메서드
        return lottoNumbers
    }
}