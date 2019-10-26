package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage implements OfferStorage {

    private List<Offer> offers = new ArrayList<>();
    private long offerCount = 0;

    @Override
    public long addOffer(Offer offer) {
        long newId = offerCount;
        offers.add(offer);
        offerCount += 1;
        return newId;
    }

    @Override
    public List<Offer> queryOffers() {
        List<Offer> returnOffers = new ArrayList<>();
        returnOffers.addAll(offers);
        return returnOffers;
    }
}
