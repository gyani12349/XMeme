package com.crio.starter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemeServiceImpl implements MemeService {
    @Autowired
    private MemeRepository memeRepository;

    // @Autowired
    // private Mod modelMapperProvider;

    @Override
    public MemeDto postMeme(String name, String url, String caption) {
        // TODO Auto-generated method stub
        if (memeRepository.existsByNameAndUrlAndCaption(name, url, caption)) {
            throw new RuntimeException("Duplicate meme detected. Unable to post.");
        }
        MemeEntity meme=new MemeEntity();
        meme.setName(name);
        meme.setUrl(url);
        meme.setCaption(caption);
        memeRepository.save(meme);
        return convertToDto(meme);
    }

    @Override
    public List<MemeDto> getLatest100Memes() {
        // TODO Auto-generated method stub
        List<MemeEntity> memes= memeRepository.findTop100ByOrderByIdDesc();
        return memes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemeDto getMemeById(String id) {
        // TODO Auto-generated method stub
        Optional<MemeEntity> memeEntity = memeRepository.findById(id);
        return memeEntity.map(this::convertToDto).orElse(null);
    }

    private MemeDto convertToDto(MemeEntity memeEntity) {
        return new MemeDto(
                memeEntity.getId(),
                memeEntity.getName(),
                memeEntity.getCaption(),
                memeEntity.getUrl()
        );
    }
    
}