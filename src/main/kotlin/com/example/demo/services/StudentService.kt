package com.example.demo.services

import com.example.demo.StudentBean

object StudentService {

    private val list = ArrayList<StudentBean>()

    //Sauvegarde un étudiant
    fun save(student: StudentBean) = list.add(student)

    //Retourne la liste des étudiants
    fun load() =  list
}