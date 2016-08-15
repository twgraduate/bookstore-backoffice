package com.thoughtworks.services;

import com.thoughtworks.model.XmlParse;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BooksServiceTest {
    BooksService booksService = new BooksService();

    @Test
    public void ShouldGetBooksFromBookServiceError() throws Exception {
        //given

        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("")), eq(HttpMethod.GET), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"error message\"}", HttpStatus.INTERNAL_SERVER_ERROR));

        //when
        ResponseEntity responseEntity = booksService.getBooks();

        //then
        assertEquals(new ResponseEntity("{\"msg\": \"error message\"}", HttpStatus.INTERNAL_SERVER_ERROR), responseEntity);
    }

    @Test
    public void ShouldGetBooksFromBookServiceSuccess() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("")), eq(HttpMethod.GET), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"success\"}", HttpStatus.OK));

        //when
        ResponseEntity responseEntity = booksService.getBooks();

        //then
        assertEquals(new ResponseEntity("{\"msg\": \"success\"}", HttpStatus.OK), responseEntity);
    }

    @Test
    public void ShouldEditBooksFromBookServiceSuccess() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("isbn")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("isbn")), eq(HttpMethod.PUT), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"Book updated\"}", HttpStatus.OK));

        //when
        ResponseEntity responseEntity = booksService.edit("isbn", new String());

        //then
        assertEquals(new ResponseEntity("{\"msg\": \"Book updated\"}", HttpStatus.OK), responseEntity);
    }

    @Test
    public void ShouldEditBooksFromBookServiceUnauthorized() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("isbn")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("isbn")), eq(HttpMethod.PUT), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED));

        //when
        ResponseEntity responseEntity = booksService.edit("isbn", "body");

        //then
        assertEquals(new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED), responseEntity);
    }

    @Test
    public void ShouldEditBooksFromBookServiceConflict() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("isbn")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("isbn")), eq(HttpMethod.PUT), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"error message\"}", HttpStatus.BAD_REQUEST));

        //when
        ResponseEntity responseEntity = booksService.edit("isbn", "body");

        //then
        assertEquals(new ResponseEntity<>("{\"msg\": \"error message\"}", HttpStatus.BAD_REQUEST), responseEntity);
    }

    @Test
    public void ShouldDeleteBookFromBookServiceSuccess() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("isbn")), eq(HttpMethod.DELETE), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"Book deleted\"}", HttpStatus.OK));

        //when
        ResponseEntity responseEntity = booksService.delete("isbn");
        assertEquals(new ResponseEntity<>("{\"msg\": \"Book deleted\"}", HttpStatus.OK), responseEntity);

    }

    @Test
    public void ShouldDeleteBookFromBookServiceUnauthorized() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("isbn")), eq(HttpMethod.DELETE), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED));

        //when
        ResponseEntity responseEntity = booksService.delete("isbn");
        assertEquals(new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED), responseEntity);

    }

    @Test
    public void ShouldDeleteBookFromBookServiceNotFound() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("isbn")), eq(HttpMethod.DELETE), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.BAD_REQUEST));

        //when
        ResponseEntity responseEntity = booksService.delete("isbn");
        assertEquals(new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.BAD_REQUEST), responseEntity);

    }

    @Test
    public void ShouldAddBookFromBookServiceSuccess() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("")), eq(HttpMethod.POST), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"Create a new book\"}", HttpStatus.OK));

        //when
        ResponseEntity responseEntity = booksService.add("");
        assertEquals(new ResponseEntity<>("{\"msg\": \"Create a new book\"}", HttpStatus.OK), responseEntity);

    }

    @Test
    public void ShouldAddBookFromBookServiceUnauthorized() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("")), eq(HttpMethod.POST), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED));

        //when
        ResponseEntity responseEntity = booksService.add("");
        assertEquals(new ResponseEntity<>("{\"msg\": \"username or password is error\"}", HttpStatus.UNAUTHORIZED), responseEntity);

    }

    @Test
    public void ShouldAddBookFromBookServiceParamsInvalid() throws Exception {
        //given
        XmlParse xmlParseMockedService = mock(XmlParse.class);
        RestTemplate restTemplateMockedService = mock(RestTemplate.class);
        booksService.xmlParse = xmlParseMockedService;
        booksService.restTemplate = restTemplateMockedService;
        when(xmlParseMockedService.pathParse("")).thenReturn("mock service address");
        when(restTemplateMockedService.exchange(eq(xmlParseMockedService.pathParse("")), eq(HttpMethod.POST), anyObject(), eq(Object.class))).thenReturn(new ResponseEntity("{\"msg\": \"error message\"}", HttpStatus.BAD_REQUEST));

        //when
        ResponseEntity responseEntity = booksService.add("");
        assertEquals(new ResponseEntity<>("{\"msg\": \"error message\"}", HttpStatus.BAD_REQUEST), responseEntity);

    }
}