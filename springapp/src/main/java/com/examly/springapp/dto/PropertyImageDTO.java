package com.examly.springapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing a property image")
public class PropertyImageDTO {

    @Schema(description = "Unique identifier of the image record", example = "1")
    private Long id;

    @NotNull(message = "Property ID is required")
    @Schema(description = "ID of the associated property", example = "1")
    private Long propertyId;

    @NotBlank(message = "Image URL is required")
    @Schema(description = "HTTP URL path of the image", example = "https://example.com/images/prop1.jpg")
    private String imageUrl;

    @Schema(description = "Indicates if this is the primary display image", example = "true")
    private Boolean isPrimary;

    public PropertyImageDTO() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
}
