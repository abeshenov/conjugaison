package org.cadadr.conjugaison.service;

import org.cadadr.conjugaison.domain.VerbConjugation;
import org.cadadr.conjugaison.repository.VerbConjugationRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerbConjugationService {

    private VerbConjugationRepository verbConjugationRepository;

    @Autowired
    public VerbConjugationService(@NotNull VerbConjugationRepository verbConjugationRepository) {
        this.verbConjugationRepository = verbConjugationRepository;
    }

    public void saveAll(Iterable<VerbConjugation> conjugations) {
        verbConjugationRepository.saveAll(conjugations);
    }

    public Optional<VerbConjugation> getConjugation(@NotNull String infinitif) {
        return verbConjugationRepository.findById(infinitif);
    }

}
