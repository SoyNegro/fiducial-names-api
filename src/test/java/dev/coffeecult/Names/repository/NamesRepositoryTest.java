package dev.coffeecult.Names.repository;

import dev.coffeecult.Names.model.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class NamesRepositoryTest {

    @Autowired
    private NamesRepository namesRepository;

    @BeforeEach
    public void flushDB() {
        namesRepository.deleteAll();
    }

    @Test
    public void should_find_no_name_on_empty_repository() {
        var names = namesRepository.findAll();
        assertThat(names).isEmpty();
    }

    @Test
    public void should_insert_a_name() {
        var name = namesRepository.save(
                new Name("NameToInsert")
        );
        assertThat(name).hasFieldOrPropertyWithValue("firstName", "NameToInsert");
    }

    @Test
    public void should_find_a_existing_name() {
        namesRepository.save(
                new Name("NameExist")
        );
        var exist = namesRepository.existsByFirstName("NameExist");
        assertThat(exist).isTrue();
    }

    @Test
    public void should_find_all_names() {
        var names = List.of(
                new Name("NameToInsert1"),
                new Name("NameToInsert2"),
                new Name("NameToInsert3"),
                new Name("NameToInsert4"),
                new Name("NameToInsert5")
        );
        namesRepository.saveAll(names);
        var foundNames = namesRepository.findAll();
        assertThat(foundNames).hasSize(5);
    }

    @Test
    public void should_throw_error_on_duplicates() {
        var names = List.of(
                new Name("NameToInsert1"),
                new Name("NameToInsert2"),
                new Name("NameToInsert3"),
                new Name("NameToInsert3"),
                new Name("NameToInsert5")
        );
        namesRepository.saveAll(names);
        assertThrows(DataIntegrityViolationException.class, ()-> namesRepository.findAll());
    }

}