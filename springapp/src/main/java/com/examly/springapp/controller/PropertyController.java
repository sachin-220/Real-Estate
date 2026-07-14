package com.examly.springapp.controller;

import com.examly.springapp.dto.PropertyRequestDTO;
import com.examly.springapp.dto.PropertyResponseDTO;
import com.examly.springapp.service.PropertyService;
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
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    @Operation(summary = "Create a new property", description = "Add a new real estate property listing to the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Property created successfully",
                     content = @Content(schema = @Schema(implementation = PropertyResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or validation error",
                     content = @Content(schema = @Schema(implementation = Void.class)))
    })
    public ResponseEntity<PropertyResponseDTO> createProperty(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createProperty(propertyRequestDTO));
    }

    @GetMapping
    @Operation(summary = "Retrieve all properties", description = "Fetch all properties listings currently stored.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of all properties",
                     content = @Content(schema = @Schema(implementation = PropertyResponseDTO.class)))
    })
    public ResponseEntity<List<PropertyResponseDTO>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve property by ID", description = "Get a single property by its unique identifier.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Property details found",
                     content = @Content(schema = @Schema(implementation = PropertyResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Property not found",
                     content = @Content(schema = @Schema(implementation = Void.class)))
    })
    public ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter property listings", description = "Filter properties using optional parameters such as price, bedrooms, and city.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Filtered list of properties",
                     content = @Content(schema = @Schema(implementation = PropertyResponseDTO.class)))
    })
    public ResponseEntity<List<PropertyResponseDTO>> filterProperties(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer bedrooms,
            @RequestParam(required = false) String city) {
        return ResponseEntity.ok(propertyService.filterProperties(minPrice, maxPrice, bedrooms, city));
    }
}
