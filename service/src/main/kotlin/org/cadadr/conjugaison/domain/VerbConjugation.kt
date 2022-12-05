package org.cadadr.conjugaison.domain

import kotlinx.serialization.Serializable
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Serializable
@Document
data class VerbConjugation(
    @Indexed(unique = true)
    val infinitif: String,
    val participePasse: String? = null,
    val participePresent: String? = null,
    val auxiliaire: String? = null,
    val formePronominale: String? = null,
    val formeNonPronominale: String? = null,
    val present: List<String>? = null,
    val imparfait: List<String>? = null,
    val passeSimple: List<String>? = null,
    val futurSimple: List<String>? = null,
    val passeCompose: List<String>? = null,
    val plusQueParfait: List<String>? = null,
    val passeAnterieur: List<String>? = null,
    val futurAnterieur: List<String>? = null,
    val subjonctifPresent: List<String>? = null,
    val subjonctifImparfait: List<String>? = null,
    val subjonctifPasse: List<String>? = null,
    val subjonctifPlusQueParfait: List<String>? = null,
    val conditionnelPresent: List<String>? = null,
    val conditionnelPasse: List<String>? = null,
    val conditionnelPasseII: List<String>? = null,
    val imperatif: List<String>? = null,
    val imperatifPasse: List<String>? = null,
)
