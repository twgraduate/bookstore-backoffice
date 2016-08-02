package com.thoughtworks.services;

import com.thoughtworks.model.XmlParse;
import org.springframework.http.HttpEntity;
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
    protected XmlParse xmlParse = new XmlParse();

    public ResponseEntity getBooks()  {
        ResponseEntity<String> responseEntity = null;
        try {
            String url = xmlParse.pathParse("");
            responseEntity = restTemplate.getForEntity(url, String.class);
        } catch (IOException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (SAXException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ParserConfigurationException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        HttpStatus status = responseEntity.getStatusCode();
        if(status.is5xxServerError()){
            return new ResponseEntity<>(new String("'msg':'error message'"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return responseEntity;
        }
    }

    public ResponseEntity edit(String isbn, String body) {
        HttpEntity<?> request = new HttpEntity<Object>(body);
        ResponseEntity responseEntity = null;
        try {
            responseEntity = restTemplate.getForEntity(xmlParse.pathParse(""), String.class);
        } catch (IOException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (SAXException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ParserConfigurationException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(responseEntity.getStatusCode().value()==202)
            return new ResponseEntity<>("'msg': 'Book updated'", HttpStatus.ACCEPTED);
        else if (responseEntity.getStatusCode().value()==401)
            return new ResponseEntity<>("'msg': 'username or password is error'", HttpStatus.UNAUTHORIZED);
        else
            return new ResponseEntity<>("'msg': 'error message'", HttpStatus.CONFLICT);
    }

    public ResponseEntity delete(String isbnArray) {
        HttpEntity<?> request = new HttpEntity<Object>(isbnArray);
        ResponseEntity responseEntity = null;
        try {
            responseEntity = restTemplate.getForEntity(xmlParse.pathParse(""), String.class);
        } catch (IOException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (SAXException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ParserConfigurationException e) {
            return new ResponseEntity<>(new String("error occurred when parse xml"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        HttpStatus status = responseEntity.getStatusCode();
        if(status.is4xxClientError()){
            return new ResponseEntity(new String("'msg': 'username or password is error'"),HttpStatus.UNAUTHORIZED);
        }
        else {
            return new ResponseEntity(new String("'msg': 'Book deleted'"),HttpStatus.OK);
        }
    }
}
