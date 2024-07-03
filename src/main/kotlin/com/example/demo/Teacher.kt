package com.example.demo

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Entity
@Table(name = "teacher")
data class TeacherBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String = "",
    @Column(name = "code")
    var code: Int? = null,
    //var sessionId : String? = null //Ira chercher session_id dans la table
)

@Repository
interface TeacherRepository : JpaRepository<TeacherBean, Long> {

    fun findByName(name: String): List<TeacherBean>
    fun findByNameOrderByIdDesc(name: String): List<TeacherBean>

    //Attention à utiliser le Bean et non le nom de la table
    @Query("from TeacherBean where name = ?1")
    fun maRequestPerso(name: String): List<TeacherBean>

    //Select sans toutes les colonnes
    @Query("select TeacherBean(T.name, T.code) from TeacherBean T where T.name = ?1")
    fun findTeacherWithoutId(name: String) : List<TeacherBean>

    @Transactional
    fun deleteByName(name: String): Int
}

@Service
class TeacherService(val teacherRep:TeacherRepository) {

    fun createTeacher(name:String?, code:Int) : TeacherBean {
        if(name.isNullOrBlank()){
            throw Exception("Name missing")
        }
        else if(code !in 1..10){
            throw Exception("Code incorrecte")
        }
        val teacher = TeacherBean(null, name, code)
        teacherRep.save(teacher)
        return teacher
    }

    fun getAll() = teacherRep.findAll()

}