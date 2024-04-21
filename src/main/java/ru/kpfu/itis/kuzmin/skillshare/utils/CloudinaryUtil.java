package ru.kpfu.itis.kuzmin.skillshare.utils;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {
    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", "debjgvnym");
            configMap.put("api_key", "364164765398895");
            configMap.put("api_secret", "mIIkpEjFGlusCjSM8ccwQM1AjyY");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
