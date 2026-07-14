package com.examly.springapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Request body schema for creating or updating a property")
public class PropertyRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    @Schema(description = "Title of the property listing", example = "Modern Condo")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Schema(description = "Detailed description of the property", example = "Spacious and bright updated unit")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Schema(description = "Price of the property", example = "320000.0")
    private Double price;

    @NotNull(message = "Bedrooms are required")
    @Positive(message = "Bedrooms must be positive")
    @Schema(description = "Number of bedrooms", example = "3")
    private Integer bedrooms;

    @NotNull(message = "Bathrooms are required")
    @Positive(message = "Bathrooms must be positive")
    @Schema(description = "Number of bathrooms", example = "2.0")
    private Double bathrooms;

    @NotNull(message = "Area is required")
    @Positive(message = "Area must be positive")
    @Schema(description = "Area of the property in square feet", example = "1245.0")
    private Double area;

    @NotBlank(message = "Address is required")
    @Schema(description = "Street address of the property", example = "555 Center Blvd")
    private String address;

    @NotBlank(message = "City is required")
    @Schema(description = "City where the property is located", example = "New York")
    private String city;

    @NotBlank(message = "State is required")
    @Schema(description = "State code where the property is located", example = "NY")
    private String state;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "\\d{5}(-\\d{4})?", message = "Zip code must be a valid format")
    @Schema(description = "ZIP code of the property", example = "10001")
    private String zipCode;

    @NotBlank(message = "Property type is required")
    @Schema(description = "Type of property", example = "Condo")
    private String propertyType;

    @Schema(description = "Date the property was listed", example = "2026-07-14")
    private LocalDate listingDate;

    @Schema(description = "Availability status of the property", example = "true")
    private Boolean isAvailable;

    public PropertyRequestDTO() {
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getBedrooms() { return bedrooms; }
    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }

    public Double getBathrooms() { return bathrooms; }
    public void setBathrooms(Double bathrooms) { this.bathrooms = bathrooms; }

    public Double getArea() { return area; }
    public void setArea(Double area) { this.area = area; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    public LocalDate getListingDate() { return listingDate; }
    public void setListingDate(LocalDate listingDate) { this.listingDate = listingDate; }

    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }
}
