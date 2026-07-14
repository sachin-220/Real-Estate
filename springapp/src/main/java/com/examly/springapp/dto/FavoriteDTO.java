package com.examly.springapp.dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for User Favorites")
public class FavoriteDTO {

    @Schema(description = "Unique identifier of the favorite association", example = "1")
    private Long id;

    @NotNull(message = "User ID is required")
    @Schema(description = "ID of the user who marked the property as favorite", example = "2")
    private Long userId;

    @NotNull(message = "Property ID is required")
    @Schema(description = "ID of the favorited property", example = "1")
    private Long propertyId;

    public FavoriteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
