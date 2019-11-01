package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

import java.util.Map;

public interface OfferStorage {
    long addOffer(Offer offer);
    Map<Long, Offer> queryOffers();

    /**
     * Returns Offer with associated id, or null if there is no offer has the given id.
     */
    Offer queryOffer(long id);

    /**
     * Delete the offer associated with the given id.
     *
     * @return the deleted offer, or null if there is no such offer with the given id.
     */
    Offer deleteOffer(long id);

    Map<Long, Offer> deleteOffer();
}
