package org.cadadr.conjugaison.service

import org.cadadr.conjugaison.domain.VerbConjugation
import org.cadadr.conjugaison.repository.VerbConjugationRepository
import org.springframework.stereotype.Service
import java.text.Normalizer
import java.util.*

@Service
class VerbConjugationService(
    private val verbConjugationRepository: VerbConjugationRepository
) {

    private val noAccentComparator =
        Comparator.comparing { str: String -> Normalizer.normalize(str, Normalizer.Form.NFKD) }

    private val verbs: MutableSet<String> = TreeSet(noAccentComparator)

    fun saveAll(conjugations: Iterable<VerbConjugation>) =
        verbConjugationRepository.saveAll(conjugations)
            .forEach { verbs.add(it.infinitif) }

    fun getConjugation(infinitif: String): VerbConjugation? =
        verbConjugationRepository.findById(infinitif).orElse(null)

    fun getVerbs(): List<String> = verbs.toList()

}
