package io.sh.siteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteUpController {
    @GetMapping("/check")
    public String getUrlStatus(@RequestParam String url) {
        String returnMsg;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int httpStatus = connection.getResponseCode() / 100;
            if (httpStatus != 2 && httpStatus != 3) {
                returnMsg = "DOWN";
            } else {
                returnMsg = "UP";
            }
        } catch (MalformedURLException e) {
            returnMsg = "Invalid URL";
        } catch (IOException e) {
            returnMsg = "ERROR";
        }
        return returnMsg;
    }
}
