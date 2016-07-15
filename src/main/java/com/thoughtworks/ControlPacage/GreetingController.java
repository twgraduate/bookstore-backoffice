package com.thoughtworks.ControlPacage;


import com.thoughtworks.ModlePacage.BookImfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;
import com.thoughtworks.ModlePacage.Greeting;
import org.springframework.web.client.RestTemplate;


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    static String sql = null;
    static DBConnection db1 = null;
    static ResultSet ret = null;


    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/book")
    public ResponseEntity book(){

        RestTemplate restTemplate = new RestTemplate();
        String bookImfo = restTemplate.getForObject("http://localhost:8080/trans",String.class);
        System.out.print(bookImfo.toString());
        ResponseEntity responseEntity = new ResponseEntity<>(bookImfo,HttpStatus.OK);
        return responseEntity;
    }

}
