package com.example.demo.Model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(name = "matchs")
data class MatchBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @field:NotBlank(message = "Il faut un nom d'équipe")
    @field:Size(max = 50, message = "Il faut au moins 50 caractères")
    var nomEquipe1: String = "",
    var scoreEquipe1: Int? = null,
    @field:NotBlank(message = "Il faut un nom d'équipe")
    @field:Size(max = 50, message = "Il faut au moins 50 caractères")
    var nomEquipe2: String = "",
    var scoreEquipe2: Int? = null,
    var fini: Int = 0,
)