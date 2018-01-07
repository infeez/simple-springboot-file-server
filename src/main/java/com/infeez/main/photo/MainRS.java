package com.infeez.main.photo;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class MainRS {

    private final static String DIR = "C://server_temp//";

    @PostMapping("/upload")
    public void uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        Files.write(Paths.get(DIR + System.currentTimeMillis()), file.getBytes());
    }

    @GetMapping("/images")
    public List<String> list() throws Exception {
        return Files.list(Paths.get(DIR)).map(p -> p.getFileName().toString()).collect(Collectors.toList());
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("fileName") String fileName) throws Exception {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(Files.readAllBytes(Paths.get(DIR.concat(fileName))));
    }

}
