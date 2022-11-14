package org.cadadr.conjugaison.controller;

public class VerbNotFoundException extends RuntimeException {

    public VerbNotFoundException(String infinitif) {
        super("Can't find verb " + infinitif);
    }

}
