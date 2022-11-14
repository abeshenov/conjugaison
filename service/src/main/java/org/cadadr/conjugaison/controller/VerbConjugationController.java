package org.cadadr.conjugaison.controller;

import org.cadadr.conjugaison.domain.VerbConjugation;
import org.cadadr.conjugaison.service.VerbConjugationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
public class VerbConjugationController {

    private final VerbConjugationService verbConjugationService;

    @Autowired
    public VerbConjugationController(@NotNull VerbConjugationService verbConjugationService) {
        this.verbConjugationService = verbConjugationService;
    }

    @QueryMapping
    public VerbConjugation conjugate(@Argument String verb) {
        return verbConjugationService.getConjugation(verb)
                .orElseThrow(() -> new VerbNotFoundException(verb));
    }

    @QueryMapping
    public @NotNull List<String> verbs() {
        return verbConjugationService.getVerbs();
    }

}
