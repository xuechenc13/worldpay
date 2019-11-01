package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage implements OfferStorage {

    private Map<Long, Offer> idOfferMap = new HashMap<>();
    private long idCount = 0;

    @Override
    public long addOffer(Offer offer) {
        long newId = idCount;
        idOfferMap.put(newId, offer);
        idCount += 1;
        return newId;
    }

    @Override
    public Map<Long, Offer> queryOffers() {
        Map<Long, Offer> returnOffers = new HashMap<>();
        returnOffers.putAll(idOfferMap);
        return returnOffers;
    }

    @Override
    public Offer queryOffer(long id) {

        return idOfferMap.get(id);
    }

    @Override
    public Offer deleteOffer(long id) {
        return idOfferMap.remove(id);
    }

    @Override
    public Map<Long, Offer> deleteOffer() {
        Map<Long, Offer> returnOffers = new HashMap<>();
        returnOffers.putAll(idOfferMap);
        idOfferMap.clear();
        return returnOffers;
    }
}
