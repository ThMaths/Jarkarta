package com.example.demo.Controller

import com.example.demo.Model.MatchBean
import com.example.demo.Service.MatchService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
class AdminPageController(private val matchService: MatchService) {

    @GetMapping("/adminPage")
    fun showAdminPage(model: Model): String {
        model.addAttribute("allMatchs", matchService.getAll())
        return "adminPage"
    }

    @PostMapping("/addMatch")
    fun addMatch(@ModelAttribute match: MatchBean): String {
        matchService.addMatch(match)
        return "redirect:/adminPage"
    }

    @PostMapping("/updateMatch")
    fun updateMatch(@ModelAttribute match: MatchBean): String {
        matchService.updateMatch(match)
        return "redirect:/adminPage"
    }

    @PostMapping("/deleteMatch")
    fun deleteMatch(@RequestParam id: Int): String {
        matchService.deleteMatch(id)
        return "redirect:/adminPage"
    }
}
