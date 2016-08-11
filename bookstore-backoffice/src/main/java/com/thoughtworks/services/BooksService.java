package com.thoughtworks.services;

import com.thoughtworks.model.XmlParse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class BooksService {

    protected RestTemplate restTemplate = new RestTemplate();
    @Autowired
    protected XmlParse xmlParse;

    public BooksService() {
    }

    public MultiValueMap<String, String> createHeaders(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Authorization", authHeader);

        return headers;
    }

    public void setXmlParse(XmlParse xmlParse) {
        this.xmlParse = xmlParse;
    }

    public ResponseEntity getBooks() {
        HttpEntity<Object> request = new HttpEntity<>(createHeaders("admin", "tw666"));
        ResponseEntity<Object> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(xmlParse.pathParse(""), HttpMethod.GET, request, Object.class);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return new ResponseEntity<>("{\"msg\": \"error message\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(responseEntity.getBody(), HttpStatus.OK);

    }

    public ResponseEntity add(Object bookMessage) {

        HttpEntity<Object> request = new HttpEntity<>(bookMessage, createHeaders("admin", "tw666"));
        ResponseEntity responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(xmlParse.pathParse(""), HttpMethod.POST, request, Object.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 401) {
                return new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED);
            } else if (e.getStatusCode().value() == 400) {
                return new ResponseEntity("{\"msg\": \"error message\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return new ResponseEntity("{\"msg\": \"error message\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(responseEntity.getBody(), HttpStatus.OK);
    }

    public ResponseEntity edit(String isbn, Object body) {
        HttpEntity<Object> request = new HttpEntity<>(body, createHeaders("admin", "tw666"));
        ResponseEntity responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(xmlParse.pathParse(isbn), HttpMethod.PUT, request, Object.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 401) {
                return new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED);
            } else if (e.getStatusCode().value() == 400) {
                return new ResponseEntity("{\"msg\": \"error message\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    public ResponseEntity delete(String isbnArray) {
        HttpEntity<Object> request = new HttpEntity<>(createHeaders("admin", "tw666"));
        ResponseEntity responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(xmlParse.pathParse(isbnArray), HttpMethod.DELETE, request, Object.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 401) {
                return new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED);
            } else if (e.getStatusCode().value() == 404) {
                return new ResponseEntity("{\"msg\": \"book is not found\"}", HttpStatus.UNAUTHORIZED);
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(responseEntity.getBody(), HttpStatus.OK);
    }


}
