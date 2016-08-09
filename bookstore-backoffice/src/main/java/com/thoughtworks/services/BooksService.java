package com.thoughtworks.services;

import com.thoughtworks.model.XmlParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Service
public class BooksService {

    protected RestTemplate restTemplate = new RestTemplate();

    @Autowired
    protected XmlParse xmlParse;

    public BooksService() {
    }

    public void setXmlParse(XmlParse xmlParse) {
        this.xmlParse = xmlParse;
    }

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
            return new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
        }
    }

    public ResponseEntity add(String bookMessage) {
        HttpEntity<?> request = new HttpEntity<Object>(bookMessage);
        ResponseEntity responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(xmlParse.pathParse(""), HttpMethod.POST, request, String.class);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        HttpStatus status = responseEntity.getStatusCode();
        if (status.value() == 401) {
            return new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED);
        } else if (status.value() == 409){
            return new ResponseEntity("{\"msg\": \"error message\"}", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity("{\"msg\": \"Create a new book\"}",HttpStatus.OK);
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
