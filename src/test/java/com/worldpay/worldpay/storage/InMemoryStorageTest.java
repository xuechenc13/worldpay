package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryStorageTest {

    private OfferStorage storage;

    @Before
    public void setup() {
        storage = new InMemoryStorage();
    }

    @Test
    public void addAnOffer() {
        long id = storage.addOffer(new Offer());
        Assert.assertEquals(0, id);
    }

    @Test
    public void addMultipleOffer() {
        long id = storage.addOffer(new Offer());
        Assert.assertEquals(0, id);

        id = storage.addOffer(new Offer());
        Assert.assertEquals(1, id);

        id = storage.addOffer(new Offer());
        Assert.assertEquals(2, id);
    }

    @Test
    public void queryOffersWhenEmpty() {
        Assert.assertEquals(Collections.emptyList(), storage.queryOffers());
    }

    @Test
    public void queryOffersWhenThereIsSingleOffer() {
        List<Offer> expected = new ArrayList<>();
        Offer offer = new Offer();
        expected.add(offer);

        storage.addOffer(offer);

        List<Offer> actual= storage.queryOffers();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryOffersWhenThereAreMultipleOffers() {
        List<Offer> expected = new ArrayList<>();
        Offer offer1 = new Offer();
        Offer offer2 = new Offer();
        expected.add(offer1);
        expected.add(offer2);

        storage.addOffer(offer1);
        storage.addOffer(offer2);

        List<Offer> actual= storage.queryOffers();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryOffersMultipleTimesShouldHaveSameOffers() {
        List<Offer> expected = new ArrayList<>();
        Offer offer = new Offer();
        expected.add(offer);
        storage.addOffer(offer);

        Assert.assertEquals(expected, storage.queryOffers());
        Assert.assertEquals(expected, storage.queryOffers());
    }

    @Test
    public void editQueriedOffersDoNotEffectOffersInStorage() {
        List<Offer> expected = new ArrayList<>();
        Offer offer1 = new Offer();
        Offer offer2 = new Offer();
        expected.add(offer1);
        expected.add(offer2);

        storage.addOffer(offer1);
        storage.addOffer(offer2);

        List<Offer> queriedOffers = storage.queryOffers();
        Assert.assertEquals(expected, queriedOffers);

        queriedOffers.add(new Offer());

        List<Offer> AnotherQueriedOffers = storage.queryOffers();
        Assert.assertEquals(expected, AnotherQueriedOffers);
    }
}
