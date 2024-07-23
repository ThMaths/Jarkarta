package com.example.demo.Controller

import com.example.demo.StudentBean
import com.example.demo.services.StudentService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class MyController {

    //http://localhost:8080/hello
    @GetMapping("/hello")
    fun testHello(model: Model): String {

        val myStudents = ArrayList<StudentBean>()
        myStudents.add(StudentBean("Bob", 10))
        myStudents.add(StudentBean("Boby", 18))
        myStudents.add(StudentBean("Charles", 20))

        model.addAttribute("title", "Bonjour Mathias")
        model.addAttribute("students", myStudents)

        return "templates"
    }

    //http://localhost:8080/add?name=tom&note=10
    @GetMapping("/add")
    fun add(model: Model,name: String = "", note: Int): String {
        StudentService.save(StudentBean(name, note))
        model.addAttribute("title", "Ajout de: $name")
        model.addAttribute("students", StudentService.load())
        return "templates"
    }

    //http://localhost:8080/filter?nom=yann&note=20
    @GetMapping("/filter")
    fun filter(nom: String? = "", note: Int? = null, model: Model): String{
        var filteredStudentList = StudentService.load().toList()
        if(!nom.isNullOrBlank()){
            filteredStudentList = StudentService.load().filter { it.name.equals(nom) }
        }
        if(note != null){
            filteredStudentList = StudentService.load().filter { it.note == note }
        }
        model.addAttribute("title", "Recherche : name=$nom - note=$note")
        model.addAttribute("students", filteredStudentList)
        //Nom du fichier HTML que l'on souhaite afficher
        return "templates"
    }

    //http://localhost:8080/form
    @GetMapping("/form")
    fun form(studentBean: StudentBean): String {
        println("/form")
        //Spring créera une instance de StudentBean qu'il mettra dans le model
        return "login"
    }

    @PostMapping("/formSubmit")
    fun formResponse(studentBean: StudentBean, redirect: RedirectAttributes): String {
        println("/formSubmit : $studentBean")

        try {
            if (studentBean.name.isBlank()) {
                throw Exception("Nom manquant")
            }
            else if (studentBean.note < 0) {
                throw Exception("Note positive attendue")
            }
            //Cas qui marche
            redirect.addFlashAttribute("studentBean", studentBean);
            return "redirect:formResult" // Redirection sur /formResult
        } catch (e:Exception) {
            e.printStackTrace()

            //Cas d'erreur
            //pour garder les données entrées dans le formulaire par l'utilisateur
            redirect.addFlashAttribute("studentBean", studentBean)
            //Pour transmettre le message d'erreur
            redirect.addFlashAttribute("errorMessage", e.message);
            return "redirect:form" //Redirige sur /form
        }
    }

    @GetMapping("/formResult") //Affiche la page résultat
    fun formResult(): String {
        return "studentForm" //Lance studentForm.html
    }

    //http://localhost:8080/home
    @GetMapping("/home") //Affiche la page résultat
    fun home(): String {
        return "homePage" //Lance homePage.html
    }
}