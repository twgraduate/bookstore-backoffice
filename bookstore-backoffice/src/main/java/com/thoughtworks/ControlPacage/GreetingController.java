package com.thoughtworks.ControlPacage;


import com.thoughtworks.ModlePacage.EditObj;
import com.thoughtworks.ModlePacage.XmlParse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicLong;
import com.thoughtworks.ModlePacage.Greeting;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;


@RestController
@RequestMapping("/book")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    static String sql = null;
    static DBConnection db1 = null;
    static ResultSet ret = null;
    RestTemplate restTemplate = new RestTemplate();
    XmlParse xmlParse = new XmlParse();


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity book() throws IOException, SAXException, ParserConfigurationException {
        String aa = xmlParse.pathParse("");
        String bookImfo = restTemplate.getForObject(xmlParse.pathParse(""),String.class);
        ResponseEntity responseEntity = new ResponseEntity<>(bookImfo,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT , value = "/{isbn}")
    public String edit(@PathVariable String isbn,@RequestBody String body) throws IOException, SAXException, ParserConfigurationException {
        restTemplate.put(xmlParse.pathParse(isbn),body);
        return body;
    }



}
