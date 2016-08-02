package com.thoughtworks.services;

import com.thoughtworks.model.XmlParse;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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
        when(restTemplateMockedService.getForEntity("mock service address",String.class)).thenReturn(new ResponseEntity("'msg':'error message'", INTERNAL_SERVER_ERROR));

        //when
        ResponseEntity responseEntity = booksService.getBooks();

        //then
        assertEquals( new ResponseEntity("'msg':'error message'", INTERNAL_SERVER_ERROR), responseEntity);
    }


}