package com.thoughtworks.Controller;

import com.thoughtworks.Modle.BookImfo;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransImfo {




    @RequestMapping("/trans")
    public ResponseEntity<BookImfo> trans(){
        BookImfo bookImfo = new BookImfo(2,"英语课本","ahskjakjs","tony",98.8,"url","一本好书");
        String str = objToJson(bookImfo);
        String o = new String(str);

        ResponseEntity  responseEntity = new ResponseEntity<>(o, HttpStatus.OK);
        return responseEntity;
    }

    public String objToJson(BookImfo bi){
        String str="{";
        str+="\"id\":\""+bi.id+"\",";
        str+="\"name\":"+bi.name+"\",";
        str+="\"isbn\":"+bi.isbn+"\",";
        str+="\"author\":"+bi.author+"\",";
        str+="\"price\":"+bi.price+"\",";
        str+="\"imgUrl\":"+bi.imgUrl+"\",";
        str+="\"description\":"+bi.description+"\"";
        str+="}";
        return str;

    }
}
