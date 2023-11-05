package com.doruk.parakeet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParakeetApplication

fun main(args: Array<String>) {
	runApplication<ParakeetApplication>(*args)
}
