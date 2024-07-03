package com.example.demo.apirest

import com.example.demo.PlaneBean
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/airport")
class AirportAPI {

    companion object{
        const val RETURN_OK = 200
        const val RETURN_INCORRECT_DATA = 214
        const val RETURN_BUSY_PLACE = 215
        const val RETURN_EMPTY_PLACE = 215
        const val RETURN_INCORRECT_POSITION = 216
    }

    var tab = Array<PlaneBean?>(5) { null}
    //val tab2 = arrayOfNulls<PlaneBean>(5)

    //Méthode qui permet de réinitialiser les données entre 2 tests
    //http://localhost:8080/airport/reset
    @GetMapping("/reset")
    fun reset(){
        tab = Array(5) { null}
    }

    //http://localhost:8080/airport/nextplace
    @GetMapping("/nextplace")
    fun nextplace() : Int {
        println("/nextplace")

        //V1
        //        for(i in tab.indices) {
        //            if(tab[i] == null) {
        //                return i;
        //            }
        //        }

        //V2
        //return tab.indexOfFirst { it == null }

        //V3
        return tab.indexOf(null)
    }

    //http://localhost:8080/airport/park?position=3
    //JSON recu { "name":"toto", "id":"5"}
    @PostMapping("/park")
    fun park(@RequestBody plane : PlaneBean, position:Int?): Int {
        println("/park plane=$plane & position=$position")

        //Contrôle sur les données
        if(plane.name.isBlank() || plane.id.isBlank()) {
            return RETURN_INCORRECT_DATA
        }
        //Contrôle sur une position correcte
        //else if(position < 0 || position>= tab.size) {
        else if(position == null || position !in tab.indices) {
            return RETURN_INCORRECT_POSITION
        }
        //Place déjà occupée
        else if(tab[position] != null) {
            return RETURN_BUSY_PLACE
        }

        //cas qui marche
        tab[position] = plane
        return RETURN_OK
    }

    //http://localhost:8080/airport/takeoff?position=2
    @GetMapping("/takeoff")
    fun takeoff(position:Int?): Int {
        println("/takeoff position=$position")

        if(position == null || position !in tab.indices) {
            return RETURN_INCORRECT_POSITION
        }
        //place vide
        else if(tab[position] == null) {
            return RETURN_EMPTY_PLACE
        }

        tab[position] = null
        return 200
    }

    //http://localhost:8080/airport/state
    @GetMapping("/state")
    fun state(): Array<PlaneBean?> {
        println("/state")
        return tab
    }
}
