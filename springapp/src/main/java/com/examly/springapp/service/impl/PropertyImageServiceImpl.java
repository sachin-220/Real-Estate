package com.examly.springapp.service.impl;

import com.examly.springapp.dto.PropertyImageDTO;
import com.examly.springapp.exception.PropertyNotFoundException;
import com.examly.springapp.model.Property;
import com.examly.springapp.model.PropertyImage;
import com.examly.springapp.repository.PropertyImageRepository;
import com.examly.springapp.repository.PropertyRepository;
import com.examly.springapp.service.PropertyImageService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyImageServiceImpl implements PropertyImageService {

    private static final Logger logger = LoggerFactory.getLogger(PropertyImageServiceImpl.class);

    private final PropertyImageRepository propertyImageRepository;
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public PropertyImageServiceImpl(PropertyImageRepository propertyImageRepository,
                                   PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.propertyImageRepository = propertyImageRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PropertyImageDTO addPropertyImage(PropertyImageDTO propertyImageDTO) {
        logger.info("Adding property image to Property ID: {}", propertyImageDTO.getPropertyId());
        Property property = propertyRepository.findById(propertyImageDTO.getPropertyId())
                .orElseThrow(() -> new PropertyNotFoundException("Property not found."));

        PropertyImage image = new PropertyImage();
        image.setProperty(property);
        image.setImageUrl(propertyImageDTO.getImageUrl());
        image.setIsPrimary(propertyImageDTO.getIsPrimary() != null ? propertyImageDTO.getIsPrimary() : false);

        PropertyImage savedImage = propertyImageRepository.save(image);
        return modelMapper.map(savedImage, PropertyImageDTO.class);
    }

    @Override
    public List<PropertyImageDTO> getImagesByPropertyId(Long propertyId) {
        logger.info("Retrieving images for Property ID: {}", propertyId);
        if (!propertyRepository.existsById(propertyId)) {
            throw new PropertyNotFoundException("Property not found.");
        }
        return propertyImageRepository.findByPropertyId(propertyId).stream()
                .map(image -> modelMapper.map(image, PropertyImageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePropertyImage(Long id) {
        logger.info("Deleting property image ID: {}", id);
        if (!propertyImageRepository.existsById(id)) {
            throw new RuntimeException("Property image not found.");
        }
        propertyImageRepository.deleteById(id);
    }
}
