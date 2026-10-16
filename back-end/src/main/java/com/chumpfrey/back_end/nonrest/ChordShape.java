package com.chumpfrey.back_end.nonrest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ChordShape {
    private @Id
    @GeneratedValue Long id;
    private int[] frets;
    private int[] fingers;

    public ChordShape() {}

    public ChordShape(int[] frets, int[] fingers) {
        this.frets = frets;
        this.fingers = fingers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChordShape) {
            ChordShape shape = (ChordShape)obj;
            for (int fret = 0; fret < shape.getFrets().length; ++fret) {
                if (this.frets[fret] != shape.getFrets()[fret])
                    return false;
            }

            for (int finger = 0; finger < shape.getFingers().length; ++finger) {
                if (this.fingers[finger] != shape.getFingers()[finger])
                    return false;
            }

            return true;
        }

        return false;
    }

    public int[] getFrets() {
        return this.frets;
    }
    
    public int[] getFingers() {
        return this.fingers;
    }

    public void setFret(int[] frets) {
        this.frets = frets;
    }

    public void setFinger(int[] fingers) {
        this.fingers = fingers;
    } 
}
