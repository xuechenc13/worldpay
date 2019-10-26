package com.worldpay.worldpay.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    @PostMapping(value = "/products/{id}")
    public String createProduct() {
        return "";
    }
}
