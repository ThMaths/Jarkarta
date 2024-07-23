package com.example.demo.Controller

import com.example.demo.Service.MatchService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
class AdminPageController (val matchService: MatchService){

    //http://localhost:8080/adminPage
    @GetMapping("/adminPage")
    fun showAdminPage( redirectAttributes: RedirectAttributes, session: HttpSession): String {
        return if (session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn") as Boolean) {
            "adminPage"
        } else {
            redirectAttributes.addFlashAttribute("error", "Veuillez vous connecter !")
            return "redirect:/adminForm"
        }
    }
}