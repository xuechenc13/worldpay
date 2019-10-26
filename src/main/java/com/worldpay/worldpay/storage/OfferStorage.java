package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

import java.util.List;

public interface OfferStorage {
    long addOffer(Offer offer);
    List<Offer> queryOffers();

    /**
     * Returns Offer with associated id, or null if there is no offer has the given id.
     */
    Offer queryOffer(long id);
}
