package org.cadadr.conjugaison.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;

import java.util.List;

public record VerbConjugation(
        @Id @NotNull String infinitif,
        @Nullable String participePasse,
        @Nullable String participePresent,
        @Nullable String auxiliaire,
        @Nullable String formePronominale,
        @Nullable String formeNonPronominale,
        @Nullable List<String> present,
        @Nullable List<String> imparfait,
        @Nullable List<String> passeSimple,
        @Nullable List<String> futurSimple,
        @Nullable List<String> passeCompose,
        @Nullable List<String> plusQueParfait,
        @Nullable List<String> passeAnterieur,
        @Nullable List<String> futurAnterieur,
        @Nullable List<String> subjonctifPresent,
        @Nullable List<String> subjonctifImparfait,
        @Nullable List<String> subjonctifPasse,
        @Nullable List<String> subjonctifPlusQueParfait,
        @Nullable List<String> conditionnelPresent,
        @Nullable List<String> conditionnelPasse,
        @Nullable List<String> conditionnelPasseII,
        @Nullable List<String> imperatif,
        @Nullable List<String> imperatifPasse
) {
    // ...
}
