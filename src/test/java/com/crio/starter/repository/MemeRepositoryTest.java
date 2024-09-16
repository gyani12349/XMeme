package com.crio.starter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class MemeRepositoryTest {
    @Autowired
    private MemeRepository memeRepository;
    @Test
    void testSaveAndGetById(){
        MemeEntity meme=new MemeEntity();
        meme.setName("Test User");
        meme.setUrl("https://example.com/test.jpg");
        meme.setCaption("Test Caption");
        MemeEntity savedMeme = memeRepository.save(meme);
        Optional<MemeEntity> retrievedMeme = memeRepository.findById(savedMeme.getId());
        assertTrue(retrievedMeme.isPresent());
        assertEquals("Test User", retrievedMeme.get().getName());
        assertEquals("https://example.com/test.jpg", retrievedMeme.get().getUrl());
        assertEquals("Test Caption", retrievedMeme.get().getCaption());
 

    }
}