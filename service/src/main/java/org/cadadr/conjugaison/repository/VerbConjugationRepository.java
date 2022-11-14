package org.cadadr.conjugaison.repository;

import org.cadadr.conjugaison.domain.VerbConjugation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VerbConjugationRepository extends MongoRepository<VerbConjugation, String> {

    VerbConjugation findByInfinitif(String infinitif);

}
