package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

public class InMemoryStorage implements OfferStorage {

    private long offerCount = 0;

    @Override
    public long addOffer(Offer offer) {
        long newId = offerCount;
        offerCount += 1;
        return newId;
    }
}
