package com.crio.starter.service;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.Meme;
import com.crio.starter.exchange.MemeDto;

public interface MemeService {
    MemeDto postMeme(String name, String url, String caption);

    List<MemeDto> getLatest100Memes();

    MemeDto getMemeById(String id);
}