package org.cadadr.conjugaison

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.cadadr.conjugaison.domain.VerbConjugation
import org.cadadr.conjugaison.service.VerbConjugationService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.File

@Component
class DataInitializer(
    private val verbConjugationService: VerbConjugationService
) {
    private val logger = LoggerFactory.getLogger(DataInitializer::class.java)
    private val DATA_FILE = File("verbs.json")

    init {
        init()
    }

    private fun init() {
        logger.info("Saving data to MongoDB")
        verbConjugationService.saveAll(readData())
    }

    private fun readData(): List<VerbConjugation> {
        val conjugations: List<VerbConjugation> = Json.decodeFromString(DATA_FILE.readText())
        logger.info("Read {} entries from '{}'", conjugations.size, DATA_FILE.name)
        return conjugations
    }

}
