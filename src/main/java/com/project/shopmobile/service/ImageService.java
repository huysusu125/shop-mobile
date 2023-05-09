package com.project.shopmobile.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Map;
import java.util.Objects;


@Service
public class ImageService {
    public String uploadImageToFlickr(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "du175rfkg",
                "api_key", "933821129397713",
                "api_secret", "9Qlj2mTv9Ll_R1SjPPg1EZ9ZR4g"));
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(convertToFile(file), ObjectUtils.emptyMap());
            return (String) uploadResult.get("secure_url");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
    public static File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
}
