package com.worldpay.worldpay.entity;

public class IdOfferPair {
    private long id;
    private Offer offer;

    public IdOfferPair(long id, Offer offer) {
        this.id = id;
        this.offer = offer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
