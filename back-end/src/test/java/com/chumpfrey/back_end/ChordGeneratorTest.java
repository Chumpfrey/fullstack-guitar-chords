package com.chumpfrey.back_end;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.chumpfrey.back_end.nonrest.Chord;
import com.chumpfrey.back_end.nonrest.ChordGenerator;
import com.chumpfrey.back_end.nonrest.ChordShape;
import com.chumpfrey.back_end.nonrest.NotePosition;

public class ChordGeneratorTest {
    
    @Test
    void testGenerate() {
        ChordGenerator gen = new ChordGenerator();
        String[] major = {"WW", "HW"};
        ArrayList<Chord> chords = gen.generateChords(major, "major");
        gen.generateChordShapes(chords);

        for (Chord chord : chords) {
            System.out.println("---------Start---------");
            System.out.println(chord.getName());
            for (ChordShape shape : chord.getChordShapes()) {
                System.out.println("START SHAPE");
                for (Integer num : shape.getFrets()) {
                    System.out.println(num);
                }
                
                System.out.println("START FINGERING");
                for (Integer finger: shape.getFingers()) {
                    System.out.println(finger);
                }
            }
        }
        assertNotNull(chords);
    }

    @Test 
    void testCartesian() {
        ChordGenerator gen = new ChordGenerator();
        String[] major = {"WW", "HW"};
        ArrayList<Chord> chords = gen.generateChords(major, "major");
        Chord chord = chords.get(0);
        System.out.println(chord.getName());
        ArrayList<ArrayList<NotePosition>> stuff = gen.findNotePositionsByString(chord, 0);
        ArrayList<ArrayList<NotePosition>> cart = gen.generateCartesianProduct(stuff);

        for (ArrayList<NotePosition> a : cart) {
            for (NotePosition b : a) {
                System.out.println("---");
                System.out.println(b.getFret());
                System.out.println(b.getString());
            }
            System.out.println("DONE");
        }
        assertNotNull(cart);
    }

    @Test
    void testGenerateChords() {
        ChordGenerator gen = new ChordGenerator();
        ArrayList<String> notes = new ArrayList<String>();
        String[] major = {"WW", "HW"};
        String[] minor = {"WH", "WW"};
        String[] major7 = {"WW", "HW", "WW"};
        ArrayList<Chord> chords = gen.generateChords(major, "major");

        for (Chord chord : chords) {
            System.out.println("-----------------");
            System.out.println(chord.getName() + " " + chord.getType());
            System.out.println(chord.getNotes());
        }
        assertNotNull(chords);
    }
}
