package ru.iasokolov.demo.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "int_key",
    indexes = [Index(name = "data_type_int_idx", columnList = "data_type")]
)
class IntKey(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "data_type")
    val dataType: Int,

    @Column(name = "name")
    val name: String,

    @Column(name = "lang")
    val lang: String
)