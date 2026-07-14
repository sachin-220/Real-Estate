package com.examly.springapp.controller;

import com.examly.springapp.dto.FavoriteDTO;
import com.examly.springapp.service.FavoriteService;
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
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    @Operation(summary = "Add property to favorites", description = "Mark a property listing as favorite for a user.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Property favorited successfully",
                     content = @Content(schema = @Schema(implementation = FavoriteDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request payload",
                     content = @Content(schema = @Schema(implementation = Void.class)))
    })
    public ResponseEntity<FavoriteDTO> addFavorite(@Valid @RequestBody FavoriteDTO favoriteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoriteService.addFavorite(favoriteDTO));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user favorites", description = "Fetch all properties marked as favorite by a specific user.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Favorites retrieved successfully",
                     content = @Content(schema = @Schema(implementation = FavoriteDTO.class)))
    })
    public ResponseEntity<List<FavoriteDTO>> getFavoritesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUserId(userId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove property from favorites", description = "Remove a favorite mapping by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Favorite removed successfully"),
        @ApiResponse(responseCode = "404", description = "Favorite mapping not found")
    })
    public ResponseEntity<Void> removeFavorite(@PathVariable Long id) {
        favoriteService.removeFavorite(id);
        return ResponseEntity.noContent().build();
    }
}
