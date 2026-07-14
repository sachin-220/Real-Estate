package com.examly.springapp.service;

import com.examly.springapp.dto.PropertyImageDTO;

import java.util.List;

public interface PropertyImageService {
    PropertyImageDTO addPropertyImage(PropertyImageDTO propertyImageDTO);
    List<PropertyImageDTO> getImagesByPropertyId(Long propertyId);
    void deletePropertyImage(Long id);
}
