package com.examly.springapp.controller;

import com.examly.springapp.dto.PropertyImageDTO;
import com.examly.springapp.model.Property;
import com.examly.springapp.repository.PropertyImageRepository;
import com.examly.springapp.repository.PropertyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PropertyImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    private Property property;

    @BeforeEach
    public void setup() {
        propertyImageRepository.deleteAll();
        propertyRepository.deleteAll();

        Property p = new Property();
        p.setTitle("Modern Condo");
        p.setDescription("Spacious unit");
        p.setPrice(320000.0);
        p.setBedrooms(3);
        p.setBathrooms(2.0);
        p.setArea(1245.0);
        p.setAddress("555 Center Blvd");
        p.setCity("New York");
        p.setState("NY");
        p.setZipCode("10001");
        p.setPropertyType("Condo");
        p.setIsAvailable(true);
        property = propertyRepository.save(p);
    }

    @Test
    public void addPropertyImage_success() throws Exception {
        PropertyImageDTO dto = new PropertyImageDTO();
        dto.setPropertyId(property.getId());
        dto.setImageUrl("https://example.com/prop.png");
        dto.setIsPrimary(true);

        mockMvc.perform(post("/api/properties/images")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.propertyId").value(property.getId()))
                .andExpect(jsonPath("$.imageUrl").value("https://example.com/prop.png"))
                .andExpect(jsonPath("$.isPrimary").value(true));
    }
}
