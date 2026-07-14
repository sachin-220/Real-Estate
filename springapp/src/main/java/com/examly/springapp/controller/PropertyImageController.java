package com.examly.springapp.controller;

import com.examly.springapp.dto.PropertyImageDTO;
import com.examly.springapp.service.PropertyImageService;
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
public class PropertyImageController {

    private final PropertyImageService propertyImageService;

    public PropertyImageController(PropertyImageService propertyImageService) {
        this.propertyImageService = propertyImageService;
    }

    @PostMapping("/images")
    @Operation(summary = "Add image to property", description = "Add a photo link to a property listing.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Image added successfully",
                     content = @Content(schema = @Schema(implementation = PropertyImageDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request payload",
                     content = @Content(schema = @Schema(implementation = Void.class)))
    })
    public ResponseEntity<PropertyImageDTO> addPropertyImage(@Valid @RequestBody PropertyImageDTO propertyImageDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyImageService.addPropertyImage(propertyImageDTO));
    }

    @GetMapping("/{propertyId}/images")
    @Operation(summary = "Get property images", description = "Retrieve all image links uploaded for a specific property.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Images list retrieved",
                     content = @Content(schema = @Schema(implementation = PropertyImageDTO.class)))
    })
    public ResponseEntity<List<PropertyImageDTO>> getImagesByPropertyId(@PathVariable Long propertyId) {
        return ResponseEntity.ok(propertyImageService.getImagesByPropertyId(propertyId));
    }

    @DeleteMapping("/images/{id}")
    @Operation(summary = "Delete property image", description = "Remove a property image record by its unique ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Image deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Image record not found")
    })
    public ResponseEntity<Void> deletePropertyImage(@PathVariable Long id) {
        propertyImageService.deletePropertyImage(id);
        return ResponseEntity.noContent().build();
    }
}
