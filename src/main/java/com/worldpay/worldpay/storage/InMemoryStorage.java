package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public List<Offer> queryOffers() {
        List<Offer> returnOffers = new ArrayList<>();
        returnOffers.addAll(idOfferMap.values());
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
}
