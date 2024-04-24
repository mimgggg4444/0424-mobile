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
    // 생성자 주입을 통해 LottoRepository 인스턴스를 주입받음

    fun generateLottoNumbers(): LottoDto {
        // 로또 번호 생성 메서드
        // 1부터 45까지의 숫자를 섞은 후 앞에서 7개씩 잘라서 7개의 번호 조합을 생성
        val numbers = (1..45).shuffled().take(7).chunked(7)
        return LottoDto(numbers)
    }

    fun saveLottoNumbers(lottoDto: LottoDto): Boolean {
        // 로또 번호 저장 메서드
        // LottoRepository를 통해 로또 번호 저장
        return lottoRepository.save(lottoDto)
    }

    fun checkLottoResult(requestDto: LottoResultRequestDto): LottoResultResponseDto {
        // 로또 결과 확인 메서드
        // LottoRepository를 통해 저장된 모든 로또 번호 조회
        val savedLottoNumbers = lottoRepository.findAll()
        // 각 로또 번호에 대해 당첨 결과 계산
        val results = savedLottoNumbers.mapIndexed { index, lotto ->
            // 일치하는 번호 개수 계산
            val correctNumbers = lotto.numbers.flatten().intersect(requestDto.numbers)
            val bonusNumber = requestDto.bonusNumber
            // 일치하는 번호 개수에 따라 당첨 결과 결정
            val result = when (correctNumbers.size) {
                6 -> if (bonusNumber in lotto.numbers.flatten()) "2등" else "1등"
                5 -> "3등"
                4 -> "4등"
                3 -> "5등"
                else -> "낙첨"
            }
            LottoResult(lotto.numbers.flatten(), correctNumbers, result)
        }
        // 응답 객체 생성 및 반환
        return LottoResultResponseDto(
            index = requestDto.numbers.size,
            winningNumbers = requestDto,
            results = results
        )
    }
}