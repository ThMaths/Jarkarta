package com.example.demo.Controller

import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class AdminFormController {

    val HARDCODED_PASSWORD = "1234"

    @GetMapping("/adminForm")
    fun showLoginPage(session: HttpSession): String {
        if (session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn") as Boolean){
            return "redirect:/adminPage"
        }
        return "login"
    }

    @PostMapping("/login")
    fun handleLogin(@RequestParam("password") password: String, session: HttpSession, model: Model): String {

        if (HARDCODED_PASSWORD == password) {
            session.setAttribute("loggedIn", true)
            return "redirect:/adminPage"
        } else {
            model.addAttribute("error", "Mot de passe incorrect")
            return "login"
        }
    }
}