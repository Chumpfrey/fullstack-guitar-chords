package com.chumpfrey.back_end.nonrest;

public class NotePosition {
    private int string;
    private int fret;

    public int getString() {
        return this.string;
    }

    public int getFret() {
        return this.fret;
    }

    public void setString(int string) {
        this.string = string;
    }

    public void setFret(int fret) {
        this.fret = fret;
    }

    public NotePosition() {}

    public NotePosition(int string, int fret) {
        this.string = string;
        this.fret = fret;
    }
}
