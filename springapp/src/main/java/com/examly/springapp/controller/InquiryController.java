package com.examly.springapp.controller;

import com.examly.springapp.dto.InquiryRequestDTO;
import com.examly.springapp.dto.InquiryResponseDTO;
import com.examly.springapp.service.InquiryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping
    @Operation(summary = "Submit a property inquiry", description = "Submit a message inquiry for a real estate listing.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Inquiry created successfully",
                     content = @Content(schema = @Schema(implementation = InquiryResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request payload",
                     content = @Content(schema = @Schema(implementation = Void.class)))
    })
    public ResponseEntity<InquiryResponseDTO> createInquiry(@Valid @RequestBody InquiryRequestDTO inquiryRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inquiryService.createInquiry(inquiryRequestDTO));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user inquiries", description = "Fetch all inquiries submitted by a specific user.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of inquiries retrieved",
                     content = @Content(schema = @Schema(implementation = InquiryResponseDTO.class)))
    })
    public ResponseEntity<List<InquiryResponseDTO>> getInquiriesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(inquiryService.getInquiriesByUserId(userId));
    }

    @GetMapping("/property/{propertyId}")
    @Operation(summary = "Get property inquiries", description = "Fetch all inquiries received for a specific property.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of inquiries retrieved",
                     content = @Content(schema = @Schema(implementation = InquiryResponseDTO.class)))
    })
    public ResponseEntity<List<InquiryResponseDTO>> getInquiriesByPropertyId(@PathVariable Long propertyId) {
        return ResponseEntity.ok(inquiryService.getInquiriesByPropertyId(propertyId));
    }
}
