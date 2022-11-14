package org.cadadr.conjugaison.controller;

import org.cadadr.conjugaison.domain.VerbConjugation;
import org.cadadr.conjugaison.service.VerbConjugationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class VerbConjugationController {

    private final VerbConjugationService verbConjugationService;

    @Autowired
    public VerbConjugationController(@NotNull VerbConjugationService verbConjugationService) {
        this.verbConjugationService = verbConjugationService;
    }

    @SchemaMapping(typeName = "Query", value = "conjugate")
    public VerbConjugation conjugate(@Argument String infinitif) {
        return verbConjugationService.getConjugation(infinitif)
                .orElseThrow(() -> new VerbNotFoundException(infinitif));
    }

}
