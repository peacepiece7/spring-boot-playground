package org.delivery.api.domain.temp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/open-api/temp")
class TempApiController {

    @GetMapping("/")
    fun temp(): String {
        println("@@@ api temp @@@")
        return "Hello, kotlin!"
    }
}