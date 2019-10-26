package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;

public interface OfferStorage {
    long addOffer(Offer offer);
}
