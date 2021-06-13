package com.example.demo.service

import com.example.demo.business.PersonBusiness
import com.example.demo.exception.BusinessException
import com.example.demo.exception.NotFoundException
import com.example.demo.model.Person
import com.example.demo.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class PersonService: PersonBusiness {

    @Autowired
    val personRepository: PersonRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Person> {
        try {
            return personRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(NotFoundException::class, BusinessException::class)
    override fun load(idPerson: Long): Person {
        val optionalPerson: Optional<Person>

        try {
            optionalPerson = personRepository!!.findById(idPerson)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

        if(!optionalPerson.isPresent) {
            throw NotFoundException("No se encontro una persona con id $idPerson")
        }

        return optionalPerson.get()
    }

    @Throws(BusinessException::class)
    override fun save(person: Person): Person {
        try {
            return personRepository!!.save(person)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun update(person: Person): Person {
        val personFounded = load(person.id)

        try {
            personRepository!!.delete(personFounded)
            return personRepository!!.save(person)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idPerson: Long) {
        val personFounded = load(idPerson)

        try {
            return personRepository!!.delete(personFounded)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }
}