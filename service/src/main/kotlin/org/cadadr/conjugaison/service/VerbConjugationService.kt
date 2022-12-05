package org.cadadr.conjugaison.service

import org.cadadr.conjugaison.domain.VerbConjugation
import org.cadadr.conjugaison.repository.VerbConjugationRepository
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.text.Normalizer
import java.util.*

@Service
class VerbConjugationService(
    private val verbConjugationRepository: VerbConjugationRepository
) {

    private val noAccentComparator =
        Comparator.comparing { str: String -> Normalizer.normalize(str, Normalizer.Form.NFKD) }

    fun getConjugation(infinitif: String): VerbConjugation? =
        verbConjugationRepository
            .findOne(Example.of(VerbConjugation(infinitif = infinitif)))
            .orElse(null)

    // For the moment, the number of verbs is small
    fun getVerbs(): List<String> =
        verbConjugationRepository.infinitif()
            .map { it.infinitif }
            .sortedWith(noAccentComparator)

}
