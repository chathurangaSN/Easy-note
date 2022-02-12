package com.example.easynotes;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EasyNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyNotesApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(NoteRepository noteRepository) {
        return args -> {
            Note firstNote = new Note(
                    "First Note",
                    "Description"
            );
            noteRepository.save(firstNote);
        };
    }
}
