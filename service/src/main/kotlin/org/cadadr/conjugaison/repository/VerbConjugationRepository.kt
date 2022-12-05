package org.cadadr.conjugaison.repository

import org.bson.types.ObjectId
import org.cadadr.conjugaison.domain.VerbConjugation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface VerbConjugationRepository : MongoRepository<VerbConjugation, ObjectId> {

    @Query(value = "{}", fields = "{ 'infinitif' : 1 }")
    fun infinitif(): List<VerbConjugation>

}
