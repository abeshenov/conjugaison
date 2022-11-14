package org.cadadr.conjugaison.service;

import org.cadadr.conjugaison.domain.VerbConjugation;
import org.cadadr.conjugaison.repository.VerbConjugationRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.*;

@Service
public class VerbConjugationService {

    private final VerbConjugationRepository verbConjugationRepository;

    private final Comparator<String> noAccentComparator =
            Comparator.comparing(str -> Normalizer.normalize(str, Normalizer.Form.NFKD));
    private final Set<String> verbs = new TreeSet<>(noAccentComparator);

    @Autowired
    public VerbConjugationService(@NotNull VerbConjugationRepository verbConjugationRepository) {
        this.verbConjugationRepository = verbConjugationRepository;
    }

    public void saveAll(Iterable<VerbConjugation> conjugations) {
        verbConjugationRepository.saveAll(conjugations)
                .forEach(conjugation -> verbs.add(conjugation.infinitif()));
    }

    public @NotNull Optional<VerbConjugation> getConjugation(@NotNull String infinitif) {
        return verbConjugationRepository.findById(infinitif);
    }

    public @NotNull List<String> getVerbs() {
        return verbs.stream().toList();
    }

}
