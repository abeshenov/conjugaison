package org.cadadr.conjugaison.controller

import org.cadadr.conjugaison.domain.VerbConjugation
import org.cadadr.conjugaison.service.VerbConjugationService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class VerbConjugationController(
    private val verbConjugationService: VerbConjugationService
) {

    @QueryMapping
    fun conjugate(@Argument verb: String): VerbConjugation =
        verbConjugationService.getConjugation(verb) ?: throw VerbNotFoundException(verb)

    @QueryMapping
    fun verbs(): List<String> = verbConjugationService.getVerbs()

}
