package com.thoughtworks.services;

import com.thoughtworks.model.XmlParse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Service
public class BooksService {
    protected RestTemplate restTemplate = new RestTemplate();
    protected XmlParse xmlParse = new XmlParse();

    public ResponseEntity getBooks() throws IOException, SAXException, ParserConfigurationException {
        String bookImfo = restTemplate.getForObject(xmlParse.pathParse(""), String.class);
        return new ResponseEntity<>(bookImfo, HttpStatus.OK);
    }

    public ResponseEntity edit(String isbn,String body) throws IOException, SAXException, ParserConfigurationException {
        restTemplate.put(xmlParse.pathParse(isbn), new String(body));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity delete(String isbnArray) throws IOException, SAXException, ParserConfigurationException {
        HttpEntity<?> request = new HttpEntity<Object>(isbnArray);
        ResponseEntity responseEntity = restTemplate.exchange(xmlParse.pathParse(""), HttpMethod.DELETE, request, String.class);
        return responseEntity;
    }
}
