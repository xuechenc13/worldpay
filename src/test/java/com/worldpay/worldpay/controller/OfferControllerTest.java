package com.worldpay.worldpay.controller;

import com.worldpay.worldpay.utility.DateTimeHelper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addAnOffer() throws Exception {
        JSONObject request = new JSONObject();
        request.put("name", "Harry Potter");
        request.put("price", 450);
        request.put("expiredDate", DateTimeHelper.covertToDateString(new Date()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/offer")
                        .headers(httpHeaders)
                        .content(request.toString());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.offer.name").value("Harry Potter"))
                .andExpect(jsonPath("$.offer.price").value(450))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void addEmptyOffer() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/offer")
                        .headers(httpHeaders);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void queryOffers() throws Exception {

        RequestBuilder getRequestBuilder =
                MockMvcRequestBuilders.get("/offer");

        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void queryAnOffer() throws Exception {
        JSONObject request = new JSONObject();
        request.put("name", "Harry Potter");
        request.put("price", 450);
        request.put("expiredDate", DateTimeHelper.covertToDateString(new Date(System.currentTimeMillis() + 60 * 1000)));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/offer")
                        .headers(httpHeaders)
                        .content(request.toString());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        RequestBuilder getRequestBuilder =
                MockMvcRequestBuilders.get("/offer/0");


        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Harry Potter"));
    }

    @Test
    public void queryNoneOffer() throws Exception {

        RequestBuilder getRequestBuilder =
                MockMvcRequestBuilders.get("/offer/0");


        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isNotFound());

    }



    @Test
    public void deleteAnValidOffer() throws Exception {
        JSONObject request = new JSONObject();
        request.put("name", "Harry Potter");
        request.put("price", 450);
        request.put("expiredDate", DateTimeHelper.covertToDateString(new Date(System.currentTimeMillis() + 60 * 1000)));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/offer")
                        .headers(httpHeaders)
                        .content(request.toString());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());


        RequestBuilder getRequestBuilder =
                MockMvcRequestBuilders.delete("/offer/0");


        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Harry Potter"));
    }

    @Test
    public void deleteNonExistOffer() throws Exception {
        RequestBuilder getRequestBuilder =
                MockMvcRequestBuilders.delete("/offer/0");


        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteOffers() throws Exception {
        JSONObject request = new JSONObject();
        request.put("name", "Harry Potter");
        request.put("price", 450);
        request.put("expiredDate", DateTimeHelper.covertToDateString(new Date(System.currentTimeMillis() + 60 * 1000)));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/offer")
                        .headers(httpHeaders)
                        .content(request.toString());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        RequestBuilder deleteRequestBuilder =
                MockMvcRequestBuilders.delete("/offer");


        mockMvc.perform(deleteRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

}
