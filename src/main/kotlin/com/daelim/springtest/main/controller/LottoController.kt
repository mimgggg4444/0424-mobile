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

    @GetMapping
    fun getLottoNumbers(): ResponseEntity<LottoDto> {
        val lottoNumbers = lottoService.generateLottoNumbers()
        return ResponseEntity.ok(lottoNumbers)
    }

    @PostMapping
    fun saveLottoNumbers(@RequestBody lottoDto: LottoDto): ResponseEntity<String> {
        val isSaved = lottoService.saveLottoNumbers(lottoDto)
        return if (isSaved) {
            ResponseEntity.ok("로또 번호 저장 성공")
        } else {
            ResponseEntity("로또 번호 저장 실패", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/check")
    fun checkLottoResult(@RequestBody requestDto: LottoResultRequestDto): ResponseEntity<LottoResultResponseDto> {
        val lottoResult = lottoService.checkLottoResult(requestDto)
        return ResponseEntity.ok(lottoResult)
    }
}
