package com.examly.springapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Response payload returned for inquiry details")
public class InquiryResponseDTO {

    @Schema(description = "Unique identifier of the inquiry", example = "1")
    private Long id;

    @Schema(description = "ID of the property being inquired about", example = "1")
    private Long propertyId;

    @Schema(description = "ID of the buyer making the inquiry", example = "2")
    private Long userId;

    @Schema(description = "Inquiry message content", example = "Is the price negotiable?")
    private String message;

    @Schema(description = "Date and time the inquiry was made", example = "2026-07-14T15:30:00")
    private LocalDateTime inquiryDate;

    public InquiryResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(LocalDateTime inquiryDate) {
        this.inquiryDate = inquiryDate;
    }
}
