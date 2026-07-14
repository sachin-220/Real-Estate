package com.examly.springapp.service.impl;

import com.examly.springapp.dto.FavoriteDTO;
import com.examly.springapp.exception.PropertyNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Favorite;
import com.examly.springapp.model.Property;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FavoriteRepository;
import com.examly.springapp.repository.PropertyRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.service.FavoriteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private static final Logger logger = LoggerFactory.getLogger(FavoriteServiceImpl.class);

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserRepository userRepository,
                               PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FavoriteDTO addFavorite(FavoriteDTO favoriteDTO) {
        logger.info("Adding favorite: User ID = {}, Property ID = {}", favoriteDTO.getUserId(), favoriteDTO.getPropertyId());
        User user = userRepository.findById(favoriteDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        Property property = propertyRepository.findById(favoriteDTO.getPropertyId())
                .orElseThrow(() -> new PropertyNotFoundException("Property not found."));

        Favorite favorite = favoriteRepository.findByUserIdAndPropertyId(user.getId(), property.getId())
                .orElseGet(() -> {
                    Favorite f = new Favorite();
                    f.setUser(user);
                    f.setProperty(property);
                    return favoriteRepository.save(f);
                });

        return modelMapper.map(favorite, FavoriteDTO.class);
    }

    @Override
    public List<FavoriteDTO> getFavoritesByUserId(Long userId) {
        logger.info("Retrieving favorites for User ID: {}", userId);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found.");
        }
        return favoriteRepository.findByUserId(userId).stream()
                .map(fav -> modelMapper.map(fav, FavoriteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeFavorite(Long id) {
        logger.info("Removing favorite ID: {}", id);
        if (!favoriteRepository.existsById(id)) {
            throw new RuntimeException("Favorite not found.");
        }
        favoriteRepository.deleteById(id);
    }
}
