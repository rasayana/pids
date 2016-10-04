package pids.controller;

import java.io.ByteArrayInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource; 
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pids.data.repository.ImageFileRepository;

@RestController
@RequestMapping("/imageservice")
public class FileController {
    @Autowired
    private ImageFileRepository imageRepository;
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void createOrUpdateBackground(@RequestParam("file") MultipartFile file) {
        try { imageRepository.createOrUpdate(file.getInputStream(), file.getContentType()); } 
        catch (Exception e) { e.printStackTrace(); }
    }
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> getBackground() throws Exception {
    	byte[] imageData = imageRepository.get();
    	InputStreamResource byteArray = new InputStreamResource(new ByteArrayInputStream(imageData));
        return ResponseEntity
                .ok()
                .contentLength(imageData.length)
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_PNG_VALUE))
                .body(byteArray);
    }
}