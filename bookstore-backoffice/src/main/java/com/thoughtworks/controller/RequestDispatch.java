package com.thoughtworks.controller;

import com.thoughtworks.model.XmlParse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@RestController
@RequestMapping("/book")
public class RequestDispatch {

    RestTemplate restTemplate = new RestTemplate();
    XmlParse xmlParse = new XmlParse();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity book() throws IOException, SAXException, ParserConfigurationException {
        String bookImfo = restTemplate.getForObject(xmlParse.pathParse(""), String.class);
        ResponseEntity responseEntity = new ResponseEntity<>(bookImfo, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{isbn}")
    public ResponseEntity edit(@PathVariable String isbn, @RequestBody String body) throws IOException, SAXException, ParserConfigurationException {
        restTemplate.put(xmlParse.pathParse(isbn), new String(body));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody String isbnArray) throws IOException, SAXException, ParserConfigurationException {
        HttpEntity<?> request = new HttpEntity<Object>(isbnArray);
        ResponseEntity responseEntity = restTemplate.exchange(xmlParse.pathParse(""), HttpMethod.DELETE, request, String.class);
        return responseEntity;
    }
}
