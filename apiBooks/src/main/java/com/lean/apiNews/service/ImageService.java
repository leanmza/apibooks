/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.apiNews.service;

import com.lean.apiNews.entity.Image;
import com.lean.apiNews.exception.MyException;
import com.lean.apiNews.repository.ImageRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lean
 */
@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
     @Transactional
    public Image saveImage(MultipartFile imageFile) throws MyException {

        if (imageFile != null) {
            try {
                Image image = new Image();

                image.setName(imageFile.getName());
                image.setMime(imageFile.getContentType());
                image.setContent(imageFile.getBytes());
                return imageRepository.save(image);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }
        return null;
    }

    public Image actualizeImage(String idImage, MultipartFile imageFile) throws MyException {

        if (imageFile != null) {

            try {

                Image image = new Image();

                if (idImage != null) {
                    Optional<Image> optionalImage = imageRepository.findById(idImage);

                    if (optionalImage.isPresent()) {
                        image = optionalImage.get();
                    }
                }

                image.setName(imageFile.getName());
                image.setMime(imageFile.getContentType());
                image.setContent(imageFile.getBytes());
                return imageRepository.save(image);

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }

        return null;
    }

    @Transactional(readOnly = true)
    public List<Image> listarTodos() {
        return imageRepository.findAll();
    }
    
    
    @Transactional
    public Image getOne(String id) {
        return imageRepository.getOne(id);
    }
}
