package com.thoughtworks.controller;

import com.thoughtworks.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@RestController
@RequestMapping("/book")
public class BooksController {

    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity book() throws IOException, SAXException, ParserConfigurationException {
        ResponseEntity responseEntity = booksService.getBooks();
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Object body) throws IOException, SAXException, ParserConfigurationException {
        ResponseEntity responseEntity = booksService.add(body);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{isbn}")
    public ResponseEntity edit(@PathVariable String isbn, @RequestBody Object body) throws IOException, SAXException, ParserConfigurationException {
        ResponseEntity responseEntity = booksService.edit(isbn, body);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{isbn}")
    public ResponseEntity delete(@PathVariable String isbn) throws IOException, SAXException, ParserConfigurationException {
        ResponseEntity responseEntity = booksService.delete(isbn);
        return responseEntity;
    }

}
