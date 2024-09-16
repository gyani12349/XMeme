package com.crio.starter.exchange;

import lombok.Data;

@Data
public class MemeDto {

    private String id;
    private String name;
    private String caption;
    private String url;

    public MemeDto() {
    }

    public MemeDto(String id, String name, String caption, String url) {
        this.id = id;
        this.name = name;
        this.caption = caption;
        this.url = url;
    }
}