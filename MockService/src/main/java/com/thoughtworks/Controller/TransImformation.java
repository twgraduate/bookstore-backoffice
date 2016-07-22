package com.thoughtworks.Controller;


import com.thoughtworks.Modle.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

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
    public ResponseEntity<Book> edit(@PathVariable String isbn){
        ResponseEntity responseEntity = null;
        responseEntity = new ResponseEntity(new String("success"), HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "{/isbn}")
    public ResponseEntity<Book> delete(@PathVariable String isbn){
        ResponseEntity responseEntity = null;
        responseEntity = new ResponseEntity(new String("success"), HttpStatus.OK);
        return responseEntity;
    }

}
