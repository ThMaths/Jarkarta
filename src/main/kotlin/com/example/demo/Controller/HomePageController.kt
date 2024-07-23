package com.example.demo.Controller

import com.example.demo.Service.MatchService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomePageController(val matchService: MatchService) {

    //http://localhost:8080/home
    @GetMapping("/HomePage")
    fun homePage(model: Model) : String {
        model.addAttribute("allMatchs", matchService.getAll())

        return "homePage"
    }
}