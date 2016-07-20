package com.thoughtworks.ControlPacage;


import com.thoughtworks.ModlePacage.XmlParse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import java.io.IOException;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicLong;
import com.thoughtworks.ModlePacage.Greeting;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    static String sql = null;
    static DBConnection db1 = null;
    static ResultSet ret = null;


    @RequestMapping(value = "/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/book")
    public ResponseEntity book() throws IOException, SAXException, ParserConfigurationException {

        RestTemplate restTemplate = new RestTemplate();
        XmlParse xmlParse = new XmlParse();
        String bookImfo = restTemplate.getForObject(xmlParse.pathParse(),String.class);
        ResponseEntity responseEntity = new ResponseEntity<>(bookImfo,HttpStatus.OK);
        return responseEntity;
    }



}
