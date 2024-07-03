package com.example.demo.services

import com.example.demo.UserBean

object UserService {

    private val list = ArrayList<UserBean>()

    fun save(user: UserBean) { //On regarde s'il n'est pas déjà en base
        val userRegister = findByLogin(user.login)
        if (userRegister != null) {
            //on retire celui en base pour remplacer par celui la
            list.remove(userRegister)
        }
        list.add(user)
    }

    //Retourne la liste
    fun load() = list

    //Retourne l'utilisateur qui a ce login ou null
    fun findByLogin(login: String)  = list.find { it.login == login }


    //Retourne l'utilisateur qui a cette session ou null
    fun findBySessionId(sessionId: String?) = list.find { it.sessionId == sessionId }

}