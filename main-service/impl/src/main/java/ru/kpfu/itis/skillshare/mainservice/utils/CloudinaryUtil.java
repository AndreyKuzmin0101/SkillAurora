package ru.kpfu.itis.skillshare.mainservice.utils;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CloudinaryUtil {
    private final String cloudName;
    private final String apiKey;
    private final String apiSecret;

    public CloudinaryUtil(@Value("${cloudinary.cloud_name}") String cloudName,
                          @Value("${cloudinary.api_key}") String apiKey,
                          @Value("${cloudinary.api_secret}") String apiSecret) {
        this.cloudName = cloudName;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    private Cloudinary cloudinary;

    public Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", cloudName);
            configMap.put("api_key", apiKey);
            configMap.put("api_secret", apiSecret);
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
