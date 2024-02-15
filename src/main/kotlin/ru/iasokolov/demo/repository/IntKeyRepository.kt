package ru.iasokolov.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.iasokolov.demo.entity.IntKey

interface IntKeyRepository : JpaRepository<IntKey, Int> {
    fun findAllByDataType(dataType: Int): List<IntKey>
}