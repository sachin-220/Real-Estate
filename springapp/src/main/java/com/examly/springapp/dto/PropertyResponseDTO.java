package com.examly.springapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Response schema for returning a property listing")
public class PropertyResponseDTO {

    @Schema(description = "Unique identifier of the property", example = "1")
    private Long id;

    @Schema(description = "Title of the property listing", example = "Modern Condo")
    private String title;

    @Schema(description = "Detailed description of the property", example = "Spacious and bright updated unit")
    private String description;

    @Schema(description = "Price of the property", example = "320000.0")
    private Double price;

    @Schema(description = "Number of bedrooms", example = "3")
    private Integer bedrooms;

    @Schema(description = "Number of bathrooms", example = "2.0")
    private Double bathrooms;

    @Schema(description = "Area of the property in square feet", example = "1245.0")
    private Double area;

    @Schema(description = "Street address of the property", example = "555 Center Blvd")
    private String address;

    @Schema(description = "City where the property is located", example = "New York")
    private String city;

    @Schema(description = "State code where the property is located", example = "NY")
    private String state;

    @Schema(description = "ZIP code of the property", example = "10001")
    private String zipCode;

    @Schema(description = "Type of property", example = "Condo")
    private String propertyType;

    @Schema(description = "Date the property was listed", example = "2026-07-14")
    private LocalDate listingDate;

    @Schema(description = "Availability status of the property", example = "true")
    private Boolean isAvailable;

    public PropertyResponseDTO() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
