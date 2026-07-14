package com.examly.springapp.service.impl;

import com.examly.springapp.dto.InquiryRequestDTO;
import com.examly.springapp.dto.InquiryResponseDTO;
import com.examly.springapp.exception.PropertyNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Inquiry;
import com.examly.springapp.model.Property;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.InquiryRepository;
import com.examly.springapp.repository.PropertyRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.service.InquiryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryServiceImpl implements InquiryService {

    private static final Logger logger = LoggerFactory.getLogger(InquiryServiceImpl.class);

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public InquiryServiceImpl(InquiryRepository inquiryRepository, UserRepository userRepository,
                              PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.inquiryRepository = inquiryRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InquiryResponseDTO createInquiry(InquiryRequestDTO inquiryRequestDTO) {
        logger.info("Creating inquiry for User ID: {}, Property ID: {}", inquiryRequestDTO.getUserId(), inquiryRequestDTO.getPropertyId());
        User user = userRepository.findById(inquiryRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        Property property = propertyRepository.findById(inquiryRequestDTO.getPropertyId())
                .orElseThrow(() -> new PropertyNotFoundException("Property not found."));

        Inquiry inquiry = new Inquiry();
        inquiry.setUser(user);
        inquiry.setProperty(property);
        inquiry.setMessage(inquiryRequestDTO.getMessage());
        inquiry.setInquiryDate(LocalDateTime.now());

        Inquiry savedInquiry = inquiryRepository.save(inquiry);
        return modelMapper.map(savedInquiry, InquiryResponseDTO.class);
    }

    @Override
    public List<InquiryResponseDTO> getInquiriesByUserId(Long userId) {
        logger.info("Retrieving inquiries for User ID: {}", userId);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found.");
        }
        return inquiryRepository.findByUserId(userId).stream()
                .map(inq -> modelMapper.map(inq, InquiryResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InquiryResponseDTO> getInquiriesByPropertyId(Long propertyId) {
        logger.info("Retrieving inquiries for Property ID: {}", propertyId);
        if (!propertyRepository.existsById(propertyId)) {
            throw new PropertyNotFoundException("Property not found.");
        }
        return inquiryRepository.findByPropertyId(propertyId).stream()
                .map(inq -> modelMapper.map(inq, InquiryResponseDTO.class))
                .collect(Collectors.toList());
    }
}
