package com.daelim.springtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringTestApplication

//go
fun main(args: Array<String>) {
	runApplication<SpringTestApplication>(*args)
}
