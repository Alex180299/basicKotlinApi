package com.example.demo.controller

import com.example.demo.business.PersonBusiness
import com.example.demo.exception.BusinessException
import com.example.demo.exception.NotFoundException
import com.example.demo.model.Person
import com.example.demo.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PERSON)
class PersonController {

    @Autowired
    val personService: PersonBusiness? = null

    @GetMapping
    fun getPeople(): ResponseEntity<List<Person>> {
        return try {
            ResponseEntity.ok(personService!!.list())
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getPerson(@PathVariable id: Long): ResponseEntity<Person> {
        return try {
            val personFounded = personService!!.load(idPerson = id)
            ResponseEntity.ok(personFounded)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun savePerson(@RequestBody person: Person): ResponseEntity<Person> {
        return try {
            val personSaved = personService!!.save(person)
            ResponseEntity.ok(personSaved)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping
    fun updatePerson(@RequestBody person: Person): ResponseEntity<Person> {
        return try {
            val personSaved = personService!!.update(person)
            ResponseEntity.ok(personSaved)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            personService!!.remove(idPerson = id)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}