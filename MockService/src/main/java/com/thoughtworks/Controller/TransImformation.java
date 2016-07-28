package com.thoughtworks.controller;


import com.thoughtworks.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/books")
public class TransImformation {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Book> get(){
        ResponseEntity responseEntity = null;
        responseEntity = new ResponseEntity(new String(Book.ReadFile()), HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{isbn}")
    public  ResponseEntity<Book> getForISBN(@PathVariable String isbn){
        ResponseEntity responseEntity = null;
        responseEntity = new ResponseEntity(new String(Book.query()), HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> add(){
        ResponseEntity responseEntity = null;
        responseEntity = new ResponseEntity(new String("success"), HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT , value = "/{isbn}")
    public ResponseEntity<Book> edit(@PathVariable String isbn,@RequestBody String body) throws IOException {
        ResponseEntity responseEntity = null;
        responseEntity = new ResponseEntity(new String("success"), HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Book> delete(@RequestBody String isbnArray){
        ResponseEntity responseEntity = null;
        responseEntity = new ResponseEntity(new String(isbnArray), HttpStatus.OK);
        return responseEntity;
    }

}

