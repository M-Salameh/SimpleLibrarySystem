package com.maids.LibrarySystem.Controllers;

import com.maids.LibrarySystem.Entities.Patron;
import com.maids.LibrarySystem.IRepositries.IPatronRepository;
import com.maids.LibrarySystem.Services.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PatronControllerTest {

    @InjectMocks
    private PatronController patronController;

    @Mock
    private IPatronRepository patronRepository;

    @Mock
    private PatronService patronService;

    private Patron patron;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");
        patron.setEmail("patronTest@gmail.com");
        patron.setAddress("NY , USA");
        // Add other properties as needed
    }

    @Test
    public void testGetAllPatrons() {
        List<Patron> patrons = Arrays.asList(patron);
        when(patronRepository.findAll()).thenReturn(patrons);

        List<Patron> result = patronController.getAllPatrons();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("John Doe");
        verify(patronRepository, times(1)).findAll();
    }

    @Test
    public void testGetPatronById() {
        when(patronService.getPatronById(1L)).thenReturn(patron);

        ResponseEntity<Patron> response = patronController.getPatronById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(patron);
        verify(patronService, times(1)).getPatronById(1L);
    }

    @Test
    public void testCreatePatron() {
        when(patronService.createPatron(patron)).thenReturn(patron);

        ResponseEntity<Patron> response = patronController.createPatron(patron);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(patron);
        verify(patronService, times(1)).createPatron(patron);
    }

    @Test
    public void testUpdatePatron() {
        when(patronService.updatePatron(1L, patron)).thenReturn(patron);

        ResponseEntity<Patron> response = patronController.updatePatron(1L, patron);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(patron);
        verify(patronService, times(1)).updatePatron(1L, patron);
    }

    @Test
    public void testDeletePatron() {
        doNothing().when(patronService).deletePatron(1L);

        ResponseEntity<Void> response = patronController.deletePatron(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(patronService, times(1)).deletePatron(1L);
    }
}