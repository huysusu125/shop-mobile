package com.project.shopmobile.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;


@Service
public class ImageService {
    public String uploadImageToFlickr(String file) {
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
    public static File convertToFile(String base64String) throws IOException {
        byte[] decodedBytes = Base64.decodeBase64(base64String);

        try {
            File file = new File("test.txt");
            OutputStream os = Files.newOutputStream(file.toPath());
            os.write(decodedBytes);
            os.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
