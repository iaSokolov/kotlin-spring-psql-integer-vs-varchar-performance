package ru.iasokolov.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.iasokolov.demo.entity.VarcharKey

interface VarcharKeyRepository : JpaRepository<VarcharKey, Int> {
    fun findAllByDataType(dataType: String): List<VarcharKey>
}