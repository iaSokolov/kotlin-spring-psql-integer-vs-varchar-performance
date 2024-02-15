package ru.iasokolov.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.iasokolov.demo.entity.IntKey
import ru.iasokolov.demo.entity.VarcharKey
import ru.iasokolov.demo.repository.IntKeyRepository
import ru.iasokolov.demo.repository.VarcharKeyRepository
import java.time.Duration
import java.time.Instant

@Component
class CommandLineRunner(
    private val intKeyRepository: IntKeyRepository,
    private val varcharKeyRepository: VarcharKeyRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        while (true) {
            testSpeed()
            readln()
        }
    }

    private fun testSpeed() {
        val vk : MutableList<Long> = mutableListOf()
        val ik : MutableList<Long> = mutableListOf()

        (1..50).forEach { _ ->
            vk.add(testVarcharKey())
            ik.add(testIntKey())
        }

        println("int: ${ik.average()}")
        println("char: ${vk.average()}")
    }

    private fun testIntKey() : Long {
        val dataTypes = intKeyRepository.findAll().map { it.dataType }.distinct()

        val start = Instant.now()
        dataTypes.take(10).forEach {
            val intKey = intKeyRepository.findAllByDataType(it)
        }
        val finish = Instant.now()
        return Duration.between(start, finish).toMillis()
    }

    private fun testVarcharKey() : Long {
        val dataTypes = varcharKeyRepository.findAll().map { it.dataType }.distinct()

        val start = Instant.now()
        dataTypes.take(10).forEach {
            val varcharKey = varcharKeyRepository.findAllByDataType(it)
        }
        val finish = Instant.now()
        return Duration.between(start, finish).toMillis()
    }

    private fun createRecord() {
        val count = readln().toInt()
        if (count > 1) {
            (1..count).forEach {
                intKeyRepository.save(createIntKey(it))
                varcharKeyRepository.save(createVarcharKey(it))

            }
        }
        println("end-of-create")
    }

    private fun createIntKey(key: Int): IntKey =
        IntKey(
            id = key,
            dataType = getRandomInt(),
            name = "Name",
            lang = "Lang"
        )

    private fun createVarcharKey(key: Int): VarcharKey =
        VarcharKey(
            id = key,
            dataType = getRandomChar(),
            name = "Name",
            lang = "Lang"
        )

    private fun getRandomChar(length: Int = 4): String =
        String((1..length).map { ('A'..'Z').random() }.toCharArray())

    private fun getRandomInt(length: Int = 4) = (1..length).random()
}