package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage implements OfferStorage {

    private List<Offer> offers = new ArrayList<>();

    @Override
    public long addOffer(Offer offer) {
        long newId = offers.size();
        offers.add(offer);
        return newId;
    }

    @Override
    public List<Offer> queryOffers() {
        List<Offer> returnOffers = new ArrayList<>();
        returnOffers.addAll(offers);
        return returnOffers;
    }

    @Override
    public Offer queryOffer(long id) {
        if (id > offers.size()) {
            return null;
        }
        return offers.get((int) id);
    }
}
