package com.examly.springapp.service;

import com.examly.springapp.dto.FavoriteDTO;

import java.util.List;

public interface FavoriteService {
    FavoriteDTO addFavorite(FavoriteDTO favoriteDTO);
    List<FavoriteDTO> getFavoritesByUserId(Long userId);
    void removeFavorite(Long id);
}
