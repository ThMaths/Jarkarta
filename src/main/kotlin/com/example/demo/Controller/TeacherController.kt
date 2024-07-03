package com.example.demo.Controller

import com.example.demo.TeacherBean
import com.example.demo.TeacherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController //ou  @Controller
class TeacherController(val teacherService : TeacherService) {

    //http://localhost:8080/addTeacher?name=toto&code=3
    @GetMapping("/addTeacher")
    fun addTeacher(name:String , code:Int) : List<TeacherBean> {
        println("/addTeacher name=$name code=code")

        teacherService.createTeacher(name, code)

        return  teacherService.getAll()
    }
}