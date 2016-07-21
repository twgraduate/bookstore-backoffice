package com.thoughtworks.Controller;


import com.thoughtworks.Modle.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController

public class TransImformation {
    @RequestMapping("/books")
    public ResponseEntity<Book> books(HttpServletRequest request, HttpServletResponse response){
        ResponseEntity responseEntity = null;
        if(request.getMethod().equals("GET")){
            if(request.getParameter("isbn")!=null){
                responseEntity = new ResponseEntity(new String(Book.dataPro(1)), HttpStatus.OK);
                return responseEntity;
            }
            else {
                responseEntity = new ResponseEntity(new String(Book.dataPro(3)), HttpStatus.OK);
                return responseEntity;
            }

        }
        else if(request.getMethod().equals("POST")){
            responseEntity = new ResponseEntity(new String("success"), HttpStatus.OK);
            return responseEntity;
        }
        else if(request.getMethod().equals("PUT")){
            responseEntity = new ResponseEntity(new String("success"), HttpStatus.OK);
            return responseEntity;
        }
        else if(request.getMethod().equals("DELETE")){
            responseEntity = new ResponseEntity(new String("success"), HttpStatus.OK);
            return responseEntity;
        }
        return responseEntity;
    }

}
