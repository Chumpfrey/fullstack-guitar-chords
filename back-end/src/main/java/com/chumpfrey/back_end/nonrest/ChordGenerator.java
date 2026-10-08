package com.chumpfrey.back_end.nonrest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class ChordGenerator {
    private static Fretboard fretboard = new Fretboard();

    private static final char HALF_STEP = 'H';
    private static final char WHOLE_STEP = 'W';

    private static final int MAX_FINGERS = 4;
    private static final int NUM_UNIQUE_NOTES = 12;
    private static final int NUM_STRINGS = 6;
    private static final int FRET_START_SEARCH = 0;
    private static final int FRET_END_SEARCH = 5;
    private static final int FRET_END = 17;
    private static final int STEP = 1;

    private ArrayList<ArrayList<Chord>> chords = new ArrayList<ArrayList<Chord>>();

    private static final String[] major = {"WW", "HW"};
    private static final String[] minor = {"WH", "WW"};
    private static final Map<String, Integer> fretNotes = fretboard.getMap();

    public ArrayList<Chord> generateChords(String[] chordInstructions, String chordType) {
        ArrayList<Chord> chords = new ArrayList<Chord>();

        for (int note = 0; note < fretNotes.size(); ++note) {
            ArrayList<String> notes = new ArrayList<String>();
            int stepsTaken = note;
            notes.add(fretboard.mapFindKey(note));
            for (int steps = 0; steps < chordInstructions.length; ++steps) {
                for (int step = 0; step < chordInstructions[steps].length(); ++step) {
                    if (chordInstructions[steps].charAt(step) == WHOLE_STEP) 
                        stepsTaken += (STEP * 2);
                    else if (chordInstructions[steps].charAt(step) == HALF_STEP) 
                        stepsTaken += STEP;
                    stepsTaken = stepsTaken % NUM_UNIQUE_NOTES;
                }
                notes.add(fretboard.mapFindKey(stepsTaken));
            }

            Chord chord = new Chord(
                fretboard.mapFindKey(note),
                chordType.toLowerCase(),
                notes
            );
            chords.add(chord);
        }

        return chords;
    }

    public ArrayList<ArrayList<NotePosition>> findNotePositionsByString(Chord chord, int windowIter) {
        ArrayList<ArrayList<NotePosition>> allNotes = new ArrayList<ArrayList<NotePosition>>();
        ArrayList<ArrayList<NotePosition>> allNotesByString = new ArrayList<ArrayList<NotePosition>>();

       for (String note : chord.getNotes()) {
            ArrayList<NotePosition> positions = fretboard.mapFindNotePositions(fretNotes.get(note), FRET_START_SEARCH + windowIter, FRET_END_SEARCH + windowIter);
            allNotes.add(positions);
       }

        for (int string = 0; string < NUM_STRINGS; ++string) {
            ArrayList<NotePosition> notesByString = new ArrayList<NotePosition>();
            for (ArrayList<NotePosition> note : allNotes) {
                for (NotePosition pos : note) {
                    if (pos.getString() == string)
                        notesByString.add(pos);
                }
            }
            allNotesByString.add(notesByString);
        }

        return allNotesByString;
    }

    public void cartesianHelper(ArrayList<ArrayList<NotePosition>> allNotes, int depth, ArrayList<NotePosition> current, 
    ArrayList<ArrayList<NotePosition>> result) {
        if (depth == allNotes.size()) {
            result.add(new ArrayList<>(current));
            return;
        }    

        ArrayList<NotePosition> currentNotes = allNotes.get(depth);
        for (NotePosition position : currentNotes) {
            current.add(position);
            cartesianHelper(allNotes, depth + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public ArrayList<ArrayList<NotePosition>> generateCartesianProduct(ArrayList<ArrayList<NotePosition>> allNotes) {
        ArrayList<ArrayList<NotePosition>> result = new ArrayList<ArrayList<NotePosition>>();
        cartesianHelper(allNotes, 0, new ArrayList<NotePosition>(), result);
        return result;
    }

    public NotePosition findLowestNotePosition(ArrayList<NotePosition> possibility) {
        NotePosition lowestPosition = new NotePosition(-1, FRET_END + 1);

        for (NotePosition position : possibility) {
            int currentFret = position.getFret();

            if (currentFret != 0 && currentFret < lowestPosition.getFret())
                lowestPosition = position; 
        }

        return lowestPosition;
    }

    public boolean isPotentialBarre(ArrayList<NotePosition> possibility) {
        boolean result = false;
        boolean adjacent = false;
        int lowestFret = findLowestNotePosition(possibility).getFret();
        int lastFret = possibility.get(0).getFret();
        int finalFret = possibility.get(possibility.size() - 1).getFret();

        for (int position = 1; position < possibility.size(); ++position) {
            int currentFret = possibility.get(position).getFret();

            if (currentFret == lowestFret && lastFret == lowestFret) 
                adjacent = true;
            
            lastFret = currentFret;
        }

        if (lowestFret != 0 && adjacent && finalFret >= lowestFret)
            result = true;

        return result;
    }

    public int[] generateFingering(ArrayList<NotePosition> possibility) {
        ArrayList<NotePosition> param = new ArrayList<>(possibility);
        int[] result = {0, 0, 0, 0, 0, 0};
        // 1 = Index, 2 = Middle, 3 = Ring, 4 = Pinky
        // > 4 is an invalid fingering assignment
        int finger = 1;

        /*THIS IMPLEMENTATION IS CURRENTLY INCORRECT
        YOU NEED TO DO MULTIPLE PASS THROUGHS
        THINK ABOUT THE A SHAPE BARRE
        YOU HAVE TWO BARRES BASICALLY
        ONE FOR THE INDEX AND ANOTHER FOR THE RING */

        if (isPotentialBarre(param)) {
            int lastFret = 0;
            for (int position = 0; position < param.size(); ++position) {
                NotePosition currPos = param.get(position);
                if (currPos.getFret() > 0) {
                    if (lastFret != 0 && lastFret != currPos.getFret())
                        ++finger;

                    result[currPos.getString()] = finger;
                    lastFret = currPos.getFret();
                }
            }   
        }
        else {
            param.sort(Comparator.comparingInt(NotePosition::getFret));

            for (int position = 0; position < param.size(); ++position) {
                NotePosition currPos = param.get(position);
                if (currPos.getFret() > 0) {
                    result[currPos.getString()] = finger;
                    ++finger;
                }
            }
        }

        return result;
    }

    public void generateChordShapes(ArrayList<Chord> chords) {
        for (Chord chord : chords) {
            ArrayList<ChordShape> shapes = new ArrayList<ChordShape>();
            for (int fretUp = 0; (fretUp + FRET_END_SEARCH) < FRET_END; ++fretUp) {
                ArrayList<ArrayList<NotePosition>> notes = findNotePositionsByString(chord, fretUp);
                ArrayList<ArrayList<NotePosition>> possibilities = generateCartesianProduct(notes);
                
                for (ArrayList<NotePosition> possibility : possibilities) {
                    ChordShape shape = new ChordShape();
                    // Create a possible chord shape
                    int[] fretShape = {
                        possibility.get(0).getFret(),
                        possibility.get(1).getFret(),
                        possibility.get(2).getFret(),
                        possibility.get(3).getFret(),
                        possibility.get(4).getFret(),
                        possibility.get(5).getFret()
                    };
                    shape.setFret(fretShape);

                    int[] fingering = generateFingering(possibility);
                    shape.setFinger(fingering);

                    boolean validShape = true;
                    for (int finger : fingering) {
                        if (finger > MAX_FINGERS) {
                            validShape = false;
                            break;
                        }
                    }

                    if (validShape)
                        shapes.add(shape);
                }
            }
            chord.setChordShapes(shapes);
        }
    }

    public ArrayList<ArrayList<Chord>> getChords() {
        return this.chords;
    }

    public ChordGenerator() {
        ArrayList<Chord> majorChords = generateChords(major, "major");
        generateChordShapes(majorChords);
        chords.add(majorChords);

        ArrayList<Chord> minorChords = generateChords(minor, "minor");
        generateChordShapes(minorChords);
        chords.add(minorChords);
    }
}
