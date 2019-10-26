package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

import java.util.List;

public interface OfferStorage {
    long addOffer(Offer offer);
    List<Offer> queryOffers();
}
