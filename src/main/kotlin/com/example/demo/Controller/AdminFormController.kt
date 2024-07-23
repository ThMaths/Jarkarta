package com.example.demo.Controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminFormController {

    //http://localhost:8080/adminForm
    @GetMapping("/adminForm")
    fun adminForm() : String {
        return "login"
    }
}