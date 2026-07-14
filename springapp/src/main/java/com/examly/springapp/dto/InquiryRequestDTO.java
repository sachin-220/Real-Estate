package com.examly.springapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for submitting an inquiry for a property")
public class InquiryRequestDTO {

    @NotNull(message = "Property ID is required")
    @Schema(description = "ID of the property being inquired about", example = "1")
    private Long propertyId;

    @NotNull(message = "User ID is required")
    @Schema(description = "ID of the buyer making the inquiry", example = "2")
    private Long userId;

    @NotBlank(message = "Message is required")
    @Size(max = 500, message = "Message must not exceed 500 characters")
    @Schema(description = "Detailed message or question about the property", example = "Is the price negotiable?")
    private String message;

    public InquiryRequestDTO() {
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
}
