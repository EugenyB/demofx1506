package com.example.demofx1506;

import com.example.demofx1506.dto.ArtistDTO;
import com.example.demofx1506.tables.Artist;

public class ArtistConverter {
    public ArtistDTO fromArtistToArtistDTO(Artist artist) {
        return ArtistDTO.builder().id(artist.getId()).name(artist.getName()).build();
    }

    public Artist fromArtistDtoToArtist(ArtistDTO artistDTO) {
        return new Artist(artistDTO.getId(), artistDTO.getName());
    }
}
