package com.project.aTicket;

import com.project.aTicket.service.EventService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.project.aTicket.controller.EventController;
import com.project.aTicket.model.*;
import com.project.aTicket.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.ArrayList;


@SpringBootTest
public class EventControllerTest {
    @Mock
    private EventService eventService;
    @InjectMocks
    private EventController eventController;
    private MockMvc mockMvc;

    public EventControllerTest() {
    }

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.eventController}).build();
    }

    @Test
    void getEventTest() throws Exception {
        List<Seating> seatingList = new ArrayList<>();
        Event event = new Event(1L, "Concierto de Rock", "Descripción del evento", "2024-12-25", seatingList);
        Mockito.when(this.eventService.getEvent(1L)).thenReturn(event);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/event/{id}", new Object[]{1L})).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id", new Object[0]).value(1L)).andExpect(MockMvcResultMatchers.jsonPath("$.title", new Object[0]).value("Concierto de Rock")).andExpect(MockMvcResultMatchers.jsonPath("$.description", new Object[0]).value("Descripción del evento")).andExpect(MockMvcResultMatchers.jsonPath("$.date", new Object[0]).value("2024-12-25"));
    }

    @Test
    void getAllEventsTest() throws Exception {
        List<Seating> seatingList = new ArrayList<>();
        Event event1 = new Event(1L, "Concierto de Rock", "Descripción del evento 1", "2024-12-25", seatingList);
        Event event2 = new Event(2L, "Teatro Clásico", "Descripción del evento 2", "2024-12-26", seatingList);
        List<Event> events = List.of(event1, event2);
        Mockito.when(this.eventService.getEvents()).thenReturn(events);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/event", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].id", new Object[0]).value(1L)).andExpect(MockMvcResultMatchers.jsonPath("$[1].id", new Object[0]).value(2L)).andExpect(MockMvcResultMatchers.jsonPath("$[0].title", new Object[0]).value("Concierto de Rock")).andExpect(MockMvcResultMatchers.jsonPath("$[1].title", new Object[0]).value("Teatro Clásico"));
    }

    @Test
    void createEventTest() throws Exception {
        List<Seating> seatingList = new ArrayList<>();
        Event event = new Event(1L, "Concierto de Rock", "Descripción del evento", "2024-12-25", seatingList);
        Mockito.when(this.eventService.createEvent((NewEventDTO)Mockito.any(NewEventDTO.class))).thenReturn(event);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/event", new Object[0]).contentType("application/json").content("{ \"title\": \"Concierto de Rock\", \"description\": \"Descripción del evento\", \"date\": \"2024-12-25\" }")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id", new Object[0]).value(1L)).andExpect(MockMvcResultMatchers.jsonPath("$.title", new Object[0]).value("Concierto de Rock"));
    }

    @Test
    void updateEventTest() throws Exception {
        List<Seating> seatingList = new ArrayList<>();
        Event event = new Event(1L, "Concierto de Rock", "Descripción del evento", "2024-12-25", seatingList);
        Mockito.when(this.eventService.updateEvent(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(event);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/event", new Object[0]).param("id", new String[]{"1"}).param("name", new String[]{"Concierto de Rock"}).param("date", new String[]{"2024-12-25"}).param("description", new String[]{"Descripción del evento"})).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id", new Object[0]).value(1L)).andExpect(MockMvcResultMatchers.jsonPath("$.title", new Object[0]).value("Concierto de Rock"));
    }

    @Test
    void deleteEventTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/event", new Object[0]).param("id", new String[]{"1"})).andExpect(MockMvcResultMatchers.status().isOk());
    }
}