package com.chumpfrey.back_end.nonrest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chumpfrey.back_end.BackEndApplication;


@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://192.168.1.85:5173",
    "http://192.168.0.243:5173"
})
@RestController
public class ChordController {
    private final BackEndApplication backEndApplication;
    private final ChordRepo repo;

    public ChordController(ChordRepo repo, BackEndApplication backEndApplication) {
        this.repo = repo;
        this.backEndApplication = backEndApplication;
    }

    @GetMapping("/chords")
    public List<Chord> all() {
        return this.repo.findAll();
    }

    @PostMapping("/chords")
    public Chord newChord(@RequestBody Chord newChord) {
        return this.repo.save(newChord);
    }

    @GetMapping("/")
    public String hello() {
        return "HELLO";
    }
    
    @GetMapping("/chords/{id}")
    public Chord one(@PathVariable Long id) {
        return this.repo.findById(id)
            .orElseThrow(() -> new ChordNotFoundException(id));
    }

    @PutMapping("/chords/{id}")
    public Chord replaceChord(@RequestBody Chord newChord, @PathVariable Long id) {
        return this.repo.findById(id)
            .map(chord -> {
                chord.setName(newChord.getName());
                chord.setNotes(newChord.getNotes());
                chord.setChordShapes(newChord.getChordShapes());
                return this.repo.save(chord);
            })
            .orElseGet(() -> {
                return this.repo.save(newChord);
            });
    }

    @DeleteMapping("/chords/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        this.repo.deleteById(id);
    }
}
