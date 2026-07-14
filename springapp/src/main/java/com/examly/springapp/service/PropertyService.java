package com.examly.springapp.service;

import com.examly.springapp.dto.PropertyRequestDTO;
import com.examly.springapp.dto.PropertyResponseDTO;

import java.util.List;

public interface PropertyService {
    PropertyResponseDTO createProperty(PropertyRequestDTO propertyRequestDTO);
    List<PropertyResponseDTO> getAllProperties();
    PropertyResponseDTO getPropertyById(Long id);
    List<PropertyResponseDTO> filterProperties(Double minPrice, Double maxPrice, Integer bedrooms, String city);
}
