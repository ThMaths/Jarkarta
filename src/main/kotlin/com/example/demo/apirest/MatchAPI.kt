package com.example.demo.apirest

import com.example.demo.Model.MatchBean
import com.example.demo.Service.MatchService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/match")
class MatchAPI(val matchService: MatchService) {

    //http://localhost:8080/matchs/allMatchs
    @PostMapping("/allMatchs")
    fun getMatchs(): List<MatchBean> {
        return matchService.getAll()
    }
}