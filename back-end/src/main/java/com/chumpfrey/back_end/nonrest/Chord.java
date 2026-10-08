package com.chumpfrey.back_end.nonrest;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Chord {
    private @Id
    @GeneratedValue Long id;
    private String name;
    private String type;
    private List<String> notes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ChordShape> shapes;

    public Chord() {}

    public Chord(String name, String type, List<String> notes) {
        this.name = name;
        this.type = type;
        this.notes = notes;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public List<String> getNotes() {
        return this.notes;
    }

    public List<ChordShape> getChordShapes() {
        return this.shapes;
    }

    public void addChordShape(ChordShape shape) {
        shapes.add(shape);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public void setChordShapes(List<ChordShape> shapes) {
        this.shapes = shapes;
    }

}
