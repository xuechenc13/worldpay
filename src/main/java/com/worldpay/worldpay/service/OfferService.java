package com.worldpay.worldpay.service;

import com.worldpay.worldpay.entity.Offer;
import com.worldpay.worldpay.storage.InMemoryStorage;
import com.worldpay.worldpay.storage.OfferStorage;

import java.util.Date;
import java.util.Map;

public class OfferService {
    private OfferStorage offerStorage;

    public OfferService() {
        offerStorage = new InMemoryStorage();
    }

    public long addOffer(Offer request) {
        return offerStorage.addOffer(request);
    }

    public Map<Long, Offer> queryOffers() {
        Map<Long, Offer> queryOffers = offerStorage.queryOffers();
        return queryOffers;
    }

    public Offer queryAnOffer(String id) {
        try {
            long queryId = Long.parseLong(id);
            return offerStorage.queryOffer(queryId);
        } catch (NumberFormatException | NullPointerException ex) {
            return null;
        }
    }
}
