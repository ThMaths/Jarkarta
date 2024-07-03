package com.example.demo.Controller

import com.example.demo.UserBean
import com.example.demo.services.UserService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/user")
class UserController {

    //Page qui affiche le formulaire
    //http://localhost:8080/user/login
    @GetMapping("/login")
    fun login(userBean: UserBean, httpSession: HttpSession): String {
        println("/login")

        //Si c'est la session d'un utilisateur déjà logué on redirige
        if (UserService.findBySessionId(httpSession.id) != null) {
            return "redirect:/user/userRegister"
        }
        else {
            return "login"
        }
    }

    //Page qui gère la réponse du formulaire
    @PostMapping("/loginSubmit")
    fun loginSubmit(userBean: UserBean,
                    httpSession: HttpSession,
                    redirectAttributes: RedirectAttributes): String {
        //Attention à ne pas laisser en production l'affichage des mots de passe dans les logs
        println("/loginSubmit : userBean=$userBean")

        //try catch de sécurité
        try {
            //Contrôle des formulaires
            if (userBean.login.isBlank()) {
                throw Exception("Login manquant")
            }
            else if (userBean.password.isBlank()) {
                throw Exception("Password manquant")
            }
            //on applique un trim pour éviter les espaces non voulu au début ou à la fin
            userBean.login = userBean.login.trim()
            userBean.password = userBean.password.trim()

            val userRegister = UserService.findByLogin(userBean.login) //On regarde si c'est un utilisateur déjà enregistré
            if (userRegister != null) { //on compare les mots de passe
                if (userRegister.password != userBean.password) {
                    throw Exception("Mot de passe incorrect")
                }
                else { //on sauvegarde sa session
                    userRegister.sessionId = httpSession.id
                    UserService.save(userRegister)
                }
            }
            else {
                //C'est un nouvel utilisateur, on l'ajoute en sauvegardant sa session
                userBean.sessionId = httpSession.id
                UserService.save(userBean)
            }
            //Pas d'erreur on redirige vers la page d'affichage
            return "redirect:/user/userRegister"
        }
        catch (e: Exception) {
            e.printStackTrace()
            //pour garder les données entrées dans le formulaire par l'utilisateur
            redirectAttributes.addFlashAttribute("userBean", userBean)
            //Pour transmettre le message d'erreur
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur : ${e.message}")
            return "redirect:/user/login"
        }
    }

    //Page qui gère l'affichage des utilisateurs enregistrés
    //http://localhost:8080/user/userRegister
    @GetMapping("/userRegister")
    fun userRegister(model: Model, session: HttpSession, redirectAttributes: RedirectAttributes): String {
        try {
            val user = UserService.findBySessionId(session.id)
            if (user == null) {
                //Non connecté on redirige vers l'écran de login
                redirectAttributes.addFlashAttribute("errorMessage", "Vous devez vous reconnecter")
                return "redirect:/user/login"
            } //on charge la liste de ceux enregistrés
            model.addAttribute("userList", UserService.load()) //De celui connecté
            model.addAttribute("userConnected", user)

            return "userRegister"
        }
        catch (e: Exception) {
            e.printStackTrace()
            //Pour transmettre le message d'erreur
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur : ${e.message}")
            //Cas d'erreur
            return "redirect:/user/login" //Redirige sur /form
        }
    }

    //http://localhost:8080/user/logout
    @GetMapping("/logout")
    fun logout(httpSession: HttpSession): String {
        println("/logout")

        //Recrée la session, autre solution la déréférencer en base
        httpSession.invalidate()

        return "redirect:/user/login"
    }
}