package dev.coffeecult.Names.service;

import dev.coffeecult.Names.model.Name;
import dev.coffeecult.Names.repository.NamesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NameServiceTest {

    @Mock
    private NamesRepository namesRepository;

    @InjectMocks
    private NameService nameService;

    @Test
    void should_return_page() {
        var pageRequest = PageRequest.of(0, 20);
        var returnedPage = new PageImpl<Name>(new ArrayList<>(), pageRequest, 5);
        when(namesRepository.findAll(pageRequest)).thenReturn(returnedPage);
        assertEquals(HttpStatus.OK, nameService.getNames(pageRequest).getStatusCode());
        assertEquals(5L, nameService.getNames(pageRequest).getBody().getTotalElements());
    }

    @Test
    void should_return_true_on_existing_name() {
        when(namesRepository.existsByFirstName(any())).thenReturn(true);
        assertEquals(HttpStatus.OK, nameService.existName("ExistingName").getStatusCode());
        assertTrue(nameService.existName("ExistingName").getBody());
    }

    @Test
    void should_return_false_on_not_existing_name() {
        when(namesRepository.existsByFirstName(any())).thenReturn(false);
        assertFalse(nameService.existName("ExistingName").getBody());
    }

    @Test
    void should_insert_names() {
        var names = Set.of(
                "FirstName",
                "SecondName",
                "ThirdName"
        );
        nameService.insertNames(names);
        verify(namesRepository, times(1)).existsByFirstName("FirstName");
        verify(namesRepository, times(1)).existsByFirstName("SecondName");
        verify(namesRepository, times(1)).existsByFirstName("ThirdName");
    }

    @Test
    void deleteName() {
    }
}