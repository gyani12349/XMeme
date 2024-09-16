package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Meme {
    private String name;
    private String caption;
    private String url;
}