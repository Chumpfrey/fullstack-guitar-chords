package com.chumpfrey.back_end.nonrest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
public class ChordController {
    private final ChordRepo repo;

    public ChordController(ChordRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/chords")
    public List<Chord> all() {
        return this.repo.findAll();
    }

    @PostMapping("/chords")
    public Chord newChord(@RequestBody Chord newChord) {
        return this.repo.save(newChord);
    }

    @DeleteMapping("/chords/{id}")
    public void deleteChord(@PathVariable Long id) {
        this.repo.deleteById(id);
    }
}
