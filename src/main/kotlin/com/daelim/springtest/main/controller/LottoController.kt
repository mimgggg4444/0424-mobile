package com.daelim.springtest.main.controller

import com.daelim.*
import com.daelim.springtest.main.api.model.dto.LottoDto
import com.daelim.springtest.main.api.model.dto.LottoResultRequestDto
import com.daelim.springtest.main.api.model.dto.LottoResultResponseDto
import com.daelim.springtest.main.service.LottoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/lotto")
class LottoController(private val lottoService: LottoService) {
    // 생성자 주입을 통해 LottoService 인스턴스를 주입받음

    @GetMapping
    fun getLottoNumbers(): ResponseEntity<LottoDto> {
        // 로또 번호 생성 API 엔드포인트
        // LottoService를 통해 로또 번호 생성
        val lottoNumbers = lottoService.generateLottoNumbers()
        // 생성된 로또 번호를 200 OK 응답으로 반환
        return ResponseEntity.ok(lottoNumbers)
    }

    @PostMapping
    fun saveLottoNumbers(@RequestBody lottoDto: LottoDto): ResponseEntity<String> {
        // 로또 번호 저장 API 엔드포인트
        // LottoService를 통해 로또 번호 저장
        val isSaved = lottoService.saveLottoNumbers(lottoDto)
        // 저장 성공 시 200 OK 응답, 실패 시 500 Internal Server Error 응답
        return if (isSaved) {
            ResponseEntity.ok("로또 번호 저장 성공")
        } else {
            ResponseEntity("로또 번호 저장 실패", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/check")
    fun checkLottoResult(@RequestBody requestDto: LottoResultRequestDto): ResponseEntity<List<LottoResultResponseDto>> {
        // 로또 결과 확인 API 엔드포인트
        // LottoService를 통해 로또 결과 확인
        val lottoResult = lottoService.checkLottoResult(requestDto)
        // 확인된 로또 결과를 리스트에 담아 200 OK 응답으로 반환
        return ResponseEntity.ok(listOf(lottoResult))
    }
}