package com.example.demo.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(val dni: Long = 0, val name: String = "", val lastName: String = "", val bornDate: Date? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}
