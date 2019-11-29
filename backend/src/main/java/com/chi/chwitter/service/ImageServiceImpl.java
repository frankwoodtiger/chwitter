package com.chi.chwitter.service;

import com.chi.chwitter.entity.Chweet;
import com.chi.chwitter.entity.Image;
import com.chi.chwitter.error.exception.ChweetNotFoundException;
import com.chi.chwitter.repository.ChweetRepository;
import com.chi.chwitter.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Service
public class ImageServiceImpl {
    @Autowired
    private ChweetRepository chweetRepository;

    public Chweet linkNewImage(MultipartFile rawImageFile, Chweet chweet) {
        validateImageParameter(rawImageFile);
        Image newImage = new Image();
        try {
            newImage.setData(rawImageFile.getBytes());
            newImage.setFileId(rawImageFile.getName());
            newImage.setFileName(rawImageFile.getName());
            newImage.setFileSize(rawImageFile.getSize());
            chweet.setImage(newImage);
            Chweet savedTweet = chweetRepository.save(chweet);
            String encodedBase64ImageString = generateEncodedBase64String(rawImageFile.getBytes());
            savedTweet.getImage().setBase64Image(encodedBase64ImageString);
            return savedTweet;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chweet;
    }

    public void validateImageParameter(MultipartFile image) {

    }

    public String generateEncodedBase64String(byte[] bytes) throws UnsupportedEncodingException {
        return new String(Base64.getEncoder().encode(bytes), "UTF-8");
    }
}
