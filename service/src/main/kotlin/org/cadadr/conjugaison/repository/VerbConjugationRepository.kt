package org.cadadr.conjugaison.repository

import org.cadadr.conjugaison.domain.VerbConjugation
import org.springframework.data.mongodb.repository.MongoRepository

interface VerbConjugationRepository : MongoRepository<VerbConjugation, String>
