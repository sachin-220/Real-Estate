package com.examly.springapp.controller;

import com.examly.springapp.dto.InquiryRequestDTO;
import com.examly.springapp.model.Property;
import com.examly.springapp.model.Role;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.InquiryRepository;
import com.examly.springapp.repository.PropertyRepository;
import com.examly.springapp.repository.UserRepository;
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
public class InquiryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private InquiryRepository inquiryRepository;

    private User user;
    private Property property;

    @BeforeEach
    public void setup() {
        inquiryRepository.deleteAll();
        propertyRepository.deleteAll();
        userRepository.deleteAll();

        User u = new User();
        u.setUsername("buyer1");
        u.setPassword("password");
        u.setEmail("buyer1@example.com");
        u.setRole(Role.BUYER);
        user = userRepository.save(u);

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
    public void createInquiry_success() throws Exception {
        InquiryRequestDTO dto = new InquiryRequestDTO();
        dto.setUserId(user.getId());
        dto.setPropertyId(property.getId());
        dto.setMessage("I am interested in this listing.");

        mockMvc.perform(post("/api/inquiries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.userId").value(user.getId()))
                .andExpect(jsonPath("$.propertyId").value(property.getId()))
                .andExpect(jsonPath("$.message").value("I am interested in this listing."));
    }
}
