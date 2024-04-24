package com.daelim.springtest.main.service


import com.daelim.*
import com.daelim.springtest.main.api.model.dto.LottoDto
import com.daelim.springtest.main.api.model.dto.LottoResult
import com.daelim.springtest.main.api.model.dto.LottoResultRequestDto
import com.daelim.springtest.main.api.model.dto.LottoResultResponseDto
import com.daelim.springtest.main.repository.LottoRepository
import org.springframework.stereotype.Service

@Service
class LottoService(private val lottoRepository: LottoRepository) {

    fun generateLottoNumbers(): LottoDto {
        val numbers = (1..45).shuffled().take(7).chunked(7)
        return LottoDto(numbers)
    }

    fun saveLottoNumbers(lottoDto: LottoDto): Boolean {
        return lottoRepository.save(lottoDto)
    }

    fun checkLottoResult(requestDto: LottoResultRequestDto): LottoResultResponseDto {
        val savedLottoNumbers = lottoRepository.findAll()
        val results = savedLottoNumbers.mapIndexed { index, lotto ->
            val correctNumbers = lotto.numbers.flatten().intersect(requestDto.numbers)
            val bonusNumber = requestDto.bonusNumber
            val result = when (correctNumbers.size) {
                6 -> if (bonusNumber in lotto.numbers.flatten()) "2등" else "1등"
                5 -> "3등"
                4 -> "4등"
                3 -> "5등"
                else -> "낙첨"
            }
            LottoResult(lotto.numbers.flatten(), correctNumbers, result)
        }
        return LottoResultResponseDto(
            index = requestDto.numbers.size,
            winningNumbers = requestDto,
            results = results
        )
    }
}