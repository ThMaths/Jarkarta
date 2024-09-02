package com.example.demo.Repository

import com.example.demo.Model.MatchBean
import org.springframework.data.jpa.repository.JpaRepository

interface MatchRepository : JpaRepository<MatchBean, Int>
