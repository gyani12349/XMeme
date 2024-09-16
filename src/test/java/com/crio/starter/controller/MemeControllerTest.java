package com.crio.starter.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.service.MemeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class MemeControllerTest {
    @MockBean
    private MemeService memeService;
    @Autowired
    private MockMvc mockMvc;
    

    @Test
    void postMeme() throws JsonProcessingException, Exception{
        MemeEntity meme=new MemeEntity();
        meme.setId("1");
        meme.setName("test");
        meme.setUrl("testUrl");
        meme.setCaption("testCaption");

        URI uri = URI.create("/memes/");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType("application/json")
        .content(new ObjectMapper().writeValueAsString(meme)));
        // resultActions.andExpect(jsonPath("$.id").value("1"))
        // .andExpect(jsonPath("$.name").value("test"))
        // .andExpect(jsonPath("$.url").value("testUrl"))
        // .andExpect(jsonPath("$.caption").value("testCaption"));
        resultActions.andExpect(status().isCreated());
    }
        
    @Test
    void testGetMemes() throws Exception {
        // Given
        MemeDto meme1 = new MemeDto();
        meme1.setId("1");
        meme1.setName("User 1");
        meme1.setUrl("https://example.com/meme1.jpg");
        meme1.setCaption("Caption 1");
        MemeDto meme2 = new MemeDto();
        meme2.setId("2");
        meme2.setName("User 2");
        meme2.setUrl("https://example.com/meme2.jpg");
        meme2.setCaption("Caption 2");
        List<MemeDto> mockMemes = Arrays.asList(meme1, meme2);
        Mockito.when(memeService.getLatest100Memes()).thenReturn(mockMemes);
        URI uri = URI.create("/memes/");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType("application/json"));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("User 1"))
                .andExpect(jsonPath("$[0].url").value("https://example.com/meme1.jpg"))
                .andExpect(jsonPath("$[0].caption").value("Caption 1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("User 2"))
                .andExpect(jsonPath("$[1].url").value("https://example.com/meme2.jpg"))
                .andExpect(jsonPath("$[1].caption").value("Caption 2"));
    }

    @Test
    void getMemeById() throws Exception{
        MemeDto meme1 = new MemeDto();
        meme1.setId("1");
        meme1.setName("TestUser");
        meme1.setUrl("https://example.com/meme1.jpg");
        meme1.setCaption("Caption test");

        Mockito.when(memeService.getMemeById(anyString())).thenReturn(meme1);
        URI uri = URI.create("/memes/1");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(uri)
        .accept("application/json"));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("TestUser"))
                .andExpect(jsonPath("$.url").value("https://example.com/meme1.jpg"))
                .andExpect(jsonPath("$.caption").value("Caption test"));

    }
}