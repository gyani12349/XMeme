package com.crio.starter.controller;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memes")
public class MemeController {
    @Autowired
    private MemeService memeService;

    @PostMapping("/")
    public ResponseEntity<?> postMeme(@RequestBody MemeDto meme){

        if (meme.getName() == null || meme.getUrl() == null || meme.getCaption() == null
        || meme.getName().trim().isEmpty() || meme.getUrl().trim().isEmpty() || meme.getCaption().trim().isEmpty()) {
    return new ResponseEntity<>("Invalid request. Name, URL, and Caption are required.", HttpStatus.BAD_REQUEST);
}

try {
    MemeDto postedMeme = memeService.postMeme(meme.getName(), meme.getUrl(), meme.getCaption());
    return new ResponseEntity<>(postedMeme, HttpStatus.CREATED);
} catch (RuntimeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
}
        // MemeEntity postedMeme=memeService.postMeme(meme.getName(), meme.getUrl(), meme.getCaption());
        // return new ResponseEntity<>(postedMeme,HttpStatus.CREATED);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<MemeDto>> getMemes(){
        List<MemeDto> memeList=memeService.getLatest100Memes();
        return new ResponseEntity<>(memeList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemeDto> getMemeById(@PathVariable String id){
        MemeDto meme=memeService.getMemeById(id);
        if(meme==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meme,HttpStatus.OK);
    }
}