package com.chumpfrey.back_end.nonrest;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger((LoadDatabase.class));

    @Bean
    CommandLineRunner initDatabase(ChordRepo repo) { 
        if (repo.count() == 0) {
            ChordGenerator gen = new ChordGenerator();

            return args -> {
                for (ArrayList <Chord> chordType : gen.getChords()) {
                    for (Chord chord : chordType) {
                        log.info("Preloading " + repo.save(chord));
                    }
                }
            };
        }
        return args -> {};
    }
}
