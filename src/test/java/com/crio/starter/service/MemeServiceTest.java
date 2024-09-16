package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Collections;
import com.crio.starter.repository.MemeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MemeServiceTest {
    @Mock
    private MemeRepository memeRepository;
    @InjectMocks
    private MemeServiceImpl memeService;

    @Test
    void getLatestMemes(){
        MockitoAnnotations.openMocks(this);
        when(memeRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, memeService.getLatest100Memes().size());
        // verify(memeRepository, times(1)).findAll();
    }
}