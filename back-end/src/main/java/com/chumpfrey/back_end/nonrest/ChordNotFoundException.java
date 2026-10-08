package com.chumpfrey.back_end.nonrest;

public class ChordNotFoundException extends RuntimeException {
    ChordNotFoundException(Long id) {
        super("Could not find specified chord " + id);
    }
}
