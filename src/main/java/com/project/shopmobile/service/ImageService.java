package com.project.shopmobile.service;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    private final Flickr flickr = new Flickr("a6f556c3484074beb37110f219675986", "229ae65c5e9d2c86", new REST());
    public Object uploadImageToFlickr(MultipartFile file) {
//        Auth auth = new Auth();
//        auth.setPermission(Permission.DELETE);
//        auth.setToken(token);
//        auth.setTokenSecret(tokenSecret);
//        flickr.setAuth(auth);
        return null;
    }
}
