package com.sorsix.gopbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GopBackendApplication

fun main(args: Array<String>) {
	runApplication<GopBackendApplication>(*args)
}
