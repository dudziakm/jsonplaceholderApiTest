package api.utils;

import api.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Helpers {

    // Method to read a file as a String
    public static String readJsonFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Method to parse JSON into a Post object
    public static Post parseJsonToPost(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Paths.get(filePath).toFile(), Post.class);
    }
}
