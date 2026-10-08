package com.chumpfrey.back_end.nonrest;

import java.util.ArrayList;
import java.util.Map;

public class Fretboard {
    private static final int NUM_STRINGS = 6;
    private static final int NUM_FRETS = 16;
    private static final int NUM_UNIQUE_NOTES = 12;
    private static final int STARTING_FRET = 0;

    private final String[] standard_tuning = {
        "E",
        "A",
        "D",
        "G",
        "B",
        "E"
    };

    private final Map<String, Integer> fretNotes = Map.ofEntries(
            Map.entry("A", 0),
            Map.entry("A#", 1),
            Map.entry("B", 2),
            Map.entry("C", 3),
            Map.entry("C#", 4),
            Map.entry("D", 5),
            Map.entry("D#", 6),
            Map.entry("E", 7),
            Map.entry("F", 8),
            Map.entry("F#", 9),
            Map.entry("G", 10),
            Map.entry("G#", 11)
        );
    
    private int[][] strings = new int[NUM_STRINGS][NUM_FRETS];

    public String mapFindKey(int value) {
        String result = "";
        for (Map.Entry<String, Integer> entry : fretNotes.entrySet()) {
            if (entry.getValue() == value)
                result = entry.getKey();
        }
        return result;
    }

    public ArrayList<NotePosition> mapFindNotePositions(int value) {
        return this.mapFindNotePositions(value, STARTING_FRET, NUM_FRETS);
    }

    public ArrayList<NotePosition> mapFindNotePositions(int value, int startRange, int endRange) {
        ArrayList<NotePosition> result = new ArrayList<NotePosition>();

        for (int string = 0; string < NUM_STRINGS; ++string) {
            for (int fret = startRange; fret < endRange; ++fret) {
                int note = strings[string][fret];

                if (note == value)
                    result.add(new NotePosition(string, fret));
            }
        }

        return result;
    }

    public Map<String, Integer> getMap() {
        return this.fretNotes;
    }
    
    public Fretboard() {
        for (int string = 0; string < NUM_STRINGS; ++string) {
            int stringTuningVal = fretNotes.get(standard_tuning[string]);

            for (int fret = 0; fret < NUM_FRETS; ++fret) {
                strings[string][fret] = (stringTuningVal + fret) % NUM_UNIQUE_NOTES;
            }
        }
    }

    public Fretboard(String[] tuning) {
        for (int string = 0; string < NUM_STRINGS; ++string) {
            int stringTuningVal = fretNotes.get(standard_tuning[string]);

            for (int fret = 0; fret < NUM_FRETS; ++fret) {
                strings[string][fret] = (stringTuningVal + fret) % NUM_UNIQUE_NOTES;
            }
        }
    }
}
