package com.example.demo.Service

import com.example.demo.Model.MatchBean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class MatchService(val matchRepository: MatchRepository){
    fun getAll(): List<MatchBean> = matchRepository.findAll()
}

@Repository
interface MatchRepository : JpaRepository<MatchBean, Long> {
}
