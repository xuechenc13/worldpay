package com.worldpay.worldpay.controller;

import com.worldpay.worldpay.entity.IdOfferPair;
import com.worldpay.worldpay.entity.Offer;
import com.worldpay.worldpay.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OfferController {
    private OfferService offerService = new OfferService();

    @PostMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public IdOfferPair addOffer(@RequestBody Offer offer) {
        long id = offerService.addOffer(offer);
        return new IdOfferPair(id, offer);
    }

    @GetMapping(value = "/offer" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  Map<Long, Offer> getOffers() {
        Map<Long, Offer> mapOffer = offerService.queryOffers();
        return mapOffer;
    }

    @GetMapping(value = "/offer/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAnOffer(@PathVariable("id") String id) {
        Offer offer = offerService.queryAnOffer(id);
        if(null != offer) {
            return ResponseEntity.ok().body(offer);
        } else {
           return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(value = "/offer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteAnOffer(@PathVariable("id") String id) {
        Offer removeOffer = offerService.deleteAnOffer(id);
        if(null != removeOffer) {
            return ResponseEntity.ok().body(removeOffer);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/offer" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteOffers() {
        Map<Long, Offer> removeOffer = offerService.deleteOffers();
        if(null != removeOffer) {
            return ResponseEntity.ok().body(removeOffer);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
