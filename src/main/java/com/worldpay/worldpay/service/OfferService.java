package com.worldpay.worldpay.service;

import com.worldpay.worldpay.entity.Offer;
import com.worldpay.worldpay.storage.InMemoryStorage;
import com.worldpay.worldpay.storage.OfferStorage;

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

    public Map<Long, Offer> deleteOffers() {
        return offerStorage.deleteOffer();
    }
    public Offer deleteAnOffer(String id) {
        try {
            long queryId = Long.parseLong(id);
            return offerStorage.deleteOffer(queryId);
        } catch (NumberFormatException | NullPointerException ex) {
            return null;
        }
    }
}
