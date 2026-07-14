package com.examly.springapp.service;

import com.examly.springapp.dto.InquiryRequestDTO;
import com.examly.springapp.dto.InquiryResponseDTO;

import java.util.List;

public interface InquiryService {
    InquiryResponseDTO createInquiry(InquiryRequestDTO inquiryRequestDTO);
    List<InquiryResponseDTO> getInquiriesByUserId(Long userId);
    List<InquiryResponseDTO> getInquiriesByPropertyId(Long propertyId);
}
