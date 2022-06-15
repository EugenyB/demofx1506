package com.example.demofx1506;

import com.example.demofx1506.dto.ArtistDTO;
import com.example.demofx1506.tables.Artist;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    private ArtistConverter artistConverter = new ArtistConverter();
    private Connection conn;
    @FXML
    private ComboBox<ArtistDTO> combobox;
    @FXML
    private Label welcomeText;

    @SneakyThrows
    public void initialize() {
        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/chinook", "student", "123");
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from artist");
        List<ArtistDTO> artists = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ArtistId");
            String name = resultSet.getString("Name");
            artists.add(artistConverter.fromArtistToArtistDTO(new Artist(id, name)));
        }
        combobox.setItems(FXCollections.observableList(artists));
    }

    @FXML
    protected void onHelloButtonClick() {
        ArtistDTO artistDTO = combobox.getSelectionModel().getSelectedItem();
        if (artistDTO == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Artist is not chosen");
            alert.show();
        } else {
            Artist artist = artistConverter.fromArtistDtoToArtist(artistDTO);
            welcomeText.setText(artist.toString());
        }
    }
}