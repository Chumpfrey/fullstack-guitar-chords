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
