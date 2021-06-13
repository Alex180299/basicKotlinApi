package com.example.demo.business

import com.example.demo.model.Person

interface PersonBusiness {

    fun list(): List<Person>
    fun load(idPerson: Long): Person
    fun save(person: Person): Person
    fun update(person: Person): Person
    fun remove(idPerson: Long)

}