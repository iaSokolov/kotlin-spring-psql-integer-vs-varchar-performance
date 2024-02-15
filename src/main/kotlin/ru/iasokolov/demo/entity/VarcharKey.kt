package ru.iasokolov.demo.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "varchar_key",
    indexes = [Index(name = "data_type_varchar_idx", columnList = "data_type")]
)
class VarcharKey(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "data_type")
    val dataType: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "lang")
    val lang: String
)