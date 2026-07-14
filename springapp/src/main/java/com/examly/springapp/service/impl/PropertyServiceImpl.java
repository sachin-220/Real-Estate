package com.examly.springapp.service.impl;

import com.examly.springapp.dto.PropertyRequestDTO;
import com.examly.springapp.dto.PropertyResponseDTO;
import com.examly.springapp.exception.PropertyNotFoundException;
import com.examly.springapp.model.Property;
import com.examly.springapp.repository.PropertyRepository;
import com.examly.springapp.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PropertyResponseDTO createProperty(PropertyRequestDTO propertyRequestDTO) {
        try {
            Property property = modelMapper.map(propertyRequestDTO, Property.class);
            if (property.getListingDate() == null) {
                property.setListingDate(LocalDate.now());
            }
            if (property.getIsAvailable() == null) {
                property.setIsAvailable(true);
            }
            Property savedProperty = propertyRepository.save(property);
            PropertyResponseDTO responseDTO = modelMapper.map(savedProperty, PropertyResponseDTO.class);
            logger.info("Property Created successfully with ID: {}", responseDTO.getId());
            return responseDTO;
        } catch (Exception e) {
            logger.error("Error creating property: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<PropertyResponseDTO> getAllProperties() {
        try {
            List<Property> properties = propertyRepository.findAll();
            List<PropertyResponseDTO> responseDTOs = properties.stream()
                    .map(property -> modelMapper.map(property, PropertyResponseDTO.class))
                    .collect(Collectors.toList());
            logger.info("Property Retrieved: All {} properties", responseDTOs.size());
            return responseDTOs;
        } catch (Exception e) {
            logger.error("Error retrieving all properties: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public PropertyResponseDTO getPropertyById(Long id) {
        try {
            Property property = propertyRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.warn("Property Not Found with ID: {}", id);
                        return new PropertyNotFoundException("Property not found.");
                    });
            PropertyResponseDTO responseDTO = modelMapper.map(property, PropertyResponseDTO.class);
            logger.info("Property Retrieved with ID: {}", id);
            return responseDTO;
        } catch (PropertyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error retrieving property with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<PropertyResponseDTO> filterProperties(Double minPrice, Double maxPrice, Integer bedrooms, String city) {
        try {
            List<Property> properties = propertyRepository.filterProperties(minPrice, maxPrice, bedrooms, city);
            List<PropertyResponseDTO> responseDTOs = properties.stream()
                    .map(property -> modelMapper.map(property, PropertyResponseDTO.class))
                    .collect(Collectors.toList());
            logger.info("Property Filtered with criteria [minPrice={}, maxPrice={}, bedrooms={}, city={}]. Found {} records",
                    minPrice, maxPrice, bedrooms, city, responseDTOs.size());
            return responseDTOs;
        } catch (Exception e) {
            logger.error("Error filtering properties: {}", e.getMessage(), e);
            throw e;
        }
    }
}
