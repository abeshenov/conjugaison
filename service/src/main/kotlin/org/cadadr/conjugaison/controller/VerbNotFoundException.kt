package org.cadadr.conjugaison.controller

class VerbNotFoundException(infinitif: String) : RuntimeException("Can't find verb $infinitif")
