//package com.thoughtworks.services;
//
//import com.sun.org.apache.xpath.internal.operations.String;
//import com.thoughtworks.model.XmlParse;
//import org.junit.Test;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
//
//public class BooksServiceTest {
//    BooksService booksService = new BooksService();
//
//    @Test
//    public void ShouldGetBooksFromBookServiceError() throws Exception {
//        //given
//
//        HttpEntity<Object> request = mock(HttpEntity.class);
//        XmlParse xmlParseMockedService = mock(XmlParse.class);
//        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
//        booksService.xmlParse = xmlParseMockedService;
//        booksService.restTemplate = restTemplateMockedService;
//        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
//        when(restTemplateMockedService.exchange("mock service address",HttpMethod.GET,request,Object.class)).thenReturn(new ResponseEntity("{\"msg\": \"error message\"}", INTERNAL_SERVER_ERROR));
//
//        //when
//        ResponseEntity responseEntity = booksService.getBooks();
//
//        //then
//        assertEquals(new ResponseEntity("{\"msg\": \"error message\"}", INTERNAL_SERVER_ERROR), responseEntity);
//    }
//
//    @Test
//    public void ShouldGetBooksFromBookServiceSuccess() throws Exception {
//        //given
//        HttpEntity<Object> request = mock(HttpEntity.class);
//        XmlParse xmlParseMockedService = mock(XmlParse.class);
//        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
//        booksService.xmlParse = xmlParseMockedService;
//        booksService.restTemplate = restTemplateMockedService;
//        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
//        when(restTemplateMockedService.exchange("mock service address",HttpMethod.GET,request,Object.class)).thenReturn(new ResponseEntity("{\"msg\": \"success\"}", HttpStatus.OK));
//
//        //when
//        ResponseEntity responseEntity = booksService.getBooks();
//
//        //then
//        assertEquals(new ResponseEntity("{\"msg\": \"success\"}", HttpStatus.OK), responseEntity);
//    }
//
//    @Test
//    public void ShouldEditBooksFromBookServiceSuccess() throws Exception {
//        //given
//        HttpEntity<Object> request = mock(HttpEntity.class);
//        XmlParse xmlParseMockedService = mock(XmlParse.class);
//        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
//        booksService.xmlParse = xmlParseMockedService;
//        booksService.restTemplate = restTemplateMockedService;
//        when(xmlParseMockedService.pathParse("isbn")).thenReturn("mock service address");
//        when(restTemplateMockedService.exchange("mock service address", HttpMethod.PUT, request, Object.class)).thenReturn(new ResponseEntity<>("{\"msg\": \"Book updated\"}", HttpStatus.OK));
//
//        //when
//        ResponseEntity responseEntity = booksService.edit("isbn", new String());
//
//        //then
//        assertEquals(new ResponseEntity("{\"msg\": \"Book updated\"}", HttpStatus.OK), responseEntity);
//    }
//
//    @Test
//    public void ShouldEditBooksFromBookServiceUnauthorized() throws Exception {
//        //given
//        HttpEntity<?> request = new HttpEntity<Object>("body");
//        XmlParse xmlParseMockedService = mock(XmlParse.class);
//        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
//        booksService.xmlParse = xmlParseMockedService;
//        booksService.restTemplate = restTemplateMockedService;
//        when(xmlParseMockedService.pathParse("isbn")).thenReturn("mock service address");
//        when(restTemplateMockedService.exchange(xmlParseMockedService.pathParse("isbn"), HttpMethod.PUT, request, String.class)).thenReturn(new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED));
//
//        //when
//        ResponseEntity responseEntity = booksService.edit("isbn", "body");
//
//        //then
//        assertEquals(new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED), responseEntity);
//    }
//
//    @Test
//    public void ShouldEditBooksFromBookServiceConflict() throws Exception {
//        //given
//        HttpEntity<?> request = new HttpEntity<Object>("body");
//        XmlParse xmlParseMockedService = mock(XmlParse.class);
//        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
//        booksService.xmlParse = xmlParseMockedService;
//        booksService.restTemplate = restTemplateMockedService;
//        when(xmlParseMockedService.pathParse("isbn")).thenReturn("mock service address");
//        when(restTemplateMockedService.exchange(xmlParseMockedService.pathParse("isbn"), HttpMethod.PUT, request, String.class)).thenReturn(new ResponseEntity<>("{\"msg\": \"error message\"}", HttpStatus.CONFLICT));
//
//        //when
//        ResponseEntity responseEntity = booksService.edit("isbn", "body");
//
//        //then
//        assertEquals(new ResponseEntity<>("{\"msg\": \"error message\"}", HttpStatus.CONFLICT), responseEntity);
//    }
//
//    @Test
//    public void ShouldDeleteBookFromBookServiceSuccess() throws Exception {
//        //given
//        HttpEntity<?> request = new HttpEntity<Object>("1,2,3");
//        XmlParse xmlParseMockedService = mock(XmlParse.class);
//        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
//        booksService.xmlParse = xmlParseMockedService;
//        booksService.restTemplate = restTemplateMockedService;
//        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
//        when(restTemplateMockedService.exchange(xmlParseMockedService.pathParse(""), HttpMethod.DELETE, request, String.class)).thenReturn(new ResponseEntity<>("{\"msg\": \"Book deleted\"}", HttpStatus.OK));
//
//        //when
//        ResponseEntity responseEntity = booksService.delete("1,2,3");
//        assertEquals(new ResponseEntity<>("{\"msg\": \"Book deleted\"}", HttpStatus.OK), responseEntity);
//
//    }
//
//    @Test
//    public void ShouldDeleteBookFromBookServiceUnauthorized() throws Exception {
//        //given
//        HttpEntity<?> request = new HttpEntity<Object>("1,2,3");
//        XmlParse xmlParseMockedService = mock(XmlParse.class);
//        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
//        booksService.xmlParse = xmlParseMockedService;
//        booksService.restTemplate = restTemplateMockedService;
//        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
//        when(restTemplateMockedService.exchange(xmlParseMockedService.pathParse(""), HttpMethod.DELETE, request, String.class)).thenReturn(new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED));
//
//        //when
//        ResponseEntity responseEntity = booksService.delete("1,2,3");
//        assertEquals(new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED), responseEntity);
//
//    }
//}