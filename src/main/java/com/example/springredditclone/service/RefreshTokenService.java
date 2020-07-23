package com.example.springredditclone.service;

import com.example.springredditclone.exceptions.SpringRedditException;
import com.example.springredditclone.model.RefreshToken;
import com.example.springredditclone.repository.RefreshTokenRepository;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
    @Autowired
    private final RefreshTokenRepository refreshTokenRepository;
    
    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        
        return refreshTokenRepository.save(refreshToken);
    }
    
    void validateRefreshToken(String token){
        refreshTokenRepository.findByToken(token).orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }
    
    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
    
}
