package com.example.demofx1506.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistDTO {
    private int id;
    private String name;

    @Override
    public String toString() {
        return id + " : " + name;
    }
}
