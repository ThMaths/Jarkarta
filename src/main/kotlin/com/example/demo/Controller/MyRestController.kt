package com.example.demo.Controller
import com.example.demo.StudentBean
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MyRestController {


    /* -------------------------------- */
    // POST
    /* -------------------------------- */

    //http://localhost:8080/increment
//Json Attendu : {"name": "toto","note": 12}
    @PostMapping("/increment")
    fun increment(@RequestBody student: StudentBean): StudentBean {
        println("/increment : $student")

        student.note++

        return student
    }

    //http://localhost:8080/receiveStudent
    //Json Attendu : {"name": "toto", "note": 12}
    @PostMapping("/receiveStudent")
    fun receiveStudent(@Valid @RequestBody student: StudentBean) {
        println("/receiveStudent : $student")
    }

    /* -------------------------------- */
    // GET
    /* -------------------------------- */

    //http://localhost:8080/boulangerie?nbCroissant=3&nbSandwich=1
    @GetMapping("/boulangerie")
    fun boulangerie(nbCroissant:Int = 0, nbSandwich:Int = 0): String {
        println("/boulangerie nbCroissant=$nbCroissant nbSandwich=$nbSandwich")

        return "%.2f".format(0.95 *   nbCroissant +  nbSandwich *4).replace(",", "€")
    }


    //http://localhost:8080/max?p1=5&p2=6
    @GetMapping("/max")
    fun max(p1:Int? = null, p2:Int? = null): Int? {
        println("/max p1=$p1 p2=$p2")

        if(p2 == null){
            return p1
        }
        else if(p1 == null) {
            return p2
        }

        return Math.max(p1, p2)
    }

    //http://localhost:8080/max?p1=5&p2=6
    @GetMapping("/max2")
    fun max2(p1:String? = null, p2:String? = null): Int? {
        println("/max p1=$p1 p2=$p2")

        var p1Int = p1?.toIntOrNull()
        var p2Int = p2?.toIntOrNull()

        if(p2Int == null){
            return p1Int
        }
        else if(p1Int == null) {
            return p2Int
        }

        return Math.max(p1Int, p2Int)
    }




    //http://localhost:8080/createStudent?name=bob&notation=12
    @GetMapping("/createStudent")
    fun createStudent(name: String = "",
                      @RequestParam(value = "notation", defaultValue = "0") note: Int): StudentBean {
        //name contiendra bob
        //note contiendra 12
        println("/createStudent : name=$name note=$note")

        return StudentBean(name, note)
    }

    //http://localhost:8080/getStudent
    @GetMapping("/getStudent")
    fun getStudent(): StudentBean {
        println("/getStudent")
        var student =  StudentBean("toto", 12)

        return student
    }

    //http://localhost:8080/test
    @GetMapping("/test")
    fun testMethode(): String {
        println("/test")

        return "helloWorld"
    }

    //http://localhost:8080/code?value=-10
    @GetMapping("/code")
    fun changerCodeRetour(value: Int, response: HttpServletResponse) {
        if (value == null) {
            //On change le code retour de la réponse qui est par défaut de 200
            response.status = 201
        }
        else if (value < 0) {
            response.status = HttpStatus.CREATED.value()
        }
        //Dans ce cas on laisse le code par défaut (ici 200)
    }
}