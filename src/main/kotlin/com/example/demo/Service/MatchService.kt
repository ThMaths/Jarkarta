package com.example.demo.Service

import com.example.demo.Model.MatchBean
import com.example.demo.Repository.MatchRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class MatchService(private val matchRepository: MatchRepository) {

    fun getAll(): List<MatchBean> = matchRepository.findAll()

    fun addMatch(match: MatchBean) {
        //val entity = match.toEntity()
        matchRepository.save(match)
    }

    fun updateMatch(match: MatchBean) {
        //val entity = match.toEntity()
        matchRepository.save(match)
    }

    fun deleteMatch(id: Int) {
        matchRepository.deleteById(id)
    }
}
