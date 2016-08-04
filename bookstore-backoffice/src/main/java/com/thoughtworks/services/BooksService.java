package com.thoughtworks.services;

import com.thoughtworks.model.XmlParse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class BooksService {

    protected RestTemplate restTemplate = new RestTemplate();

    protected XmlParse xmlParse = new XmlParse();


    public ResponseEntity getBooks() {
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.getForEntity(xmlParse.pathParse(""), String.class);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        HttpStatus status = responseEntity.getStatusCode();
        if (status.is5xxServerError()) {
            return new ResponseEntity<>("{\"msg\": \"error message\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return responseEntity;
        }
    }

    public ResponseEntity edit(String isbn, String body) {
        HttpEntity<?> request = new HttpEntity<Object>(body);
        ResponseEntity responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(xmlParse.pathParse(isbn), HttpMethod.PUT, request, String.class);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (responseEntity.getStatusCode().value() == 202)
            return new ResponseEntity<>("{\"msg\": \"book update\"}", HttpStatus.ACCEPTED);
        else if (responseEntity.getStatusCode().value() == 401)
            return new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED);
        else
            return new ResponseEntity<>("{\"msg\": \"error message\"}", HttpStatus.CONFLICT);
    }

    public ResponseEntity delete(String isbnArray) {
        HttpEntity<?> request = new HttpEntity<Object>(isbnArray);
        ResponseEntity responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(xmlParse.pathParse(""), HttpMethod.DELETE, request, String.class);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        HttpStatus status = responseEntity.getStatusCode();
        if (status.is4xxClientError()) {
            return new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity("{\"msg\": \"Book deleted\"}", HttpStatus.OK);
        }
    }


}
