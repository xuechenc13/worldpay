package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        Assert.assertEquals(Collections.emptyMap(), storage.queryOffers());
    }

    @Test
    public void queryOffersWhenThereIsSingleOffer() {
        Map<Long, Offer> expected = new HashMap<>();
        Offer offer = new Offer();
        expected.put((long) 0, offer);

        storage.addOffer(offer);

        Map<Long, Offer> actual= storage.queryOffers();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryOffersWhenThereAreMultipleOffers() {
        Map<Long, Offer> expected = new HashMap<>();
        Offer offer1 = new Offer();
        Offer offer2 = new Offer();
        expected.put((long) 0, offer1);
        expected.put((long) 1, offer2);

        storage.addOffer(offer1);
        storage.addOffer(offer2);

        Map<Long, Offer> actual= storage.queryOffers();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryOffersMultipleTimesShouldHaveSameOffers() {
        Map<Long, Offer> expected = new HashMap<>();
        Offer offer = new Offer();
        expected.put((long) 0, offer);
        storage.addOffer(offer);

        Assert.assertEquals(expected, storage.queryOffers());
        Assert.assertEquals(expected, storage.queryOffers());
    }

    @Test
    public void editQueriedOffersDoNotEffectOffersInStorage() {
        Map<Long, Offer> expected = new HashMap<>();
        Offer offer1 = new Offer();
        Offer offer2 = new Offer();
        expected.put((long) 0, offer1);
        expected.put((long) 1, offer2);

        storage.addOffer(offer1);
        storage.addOffer(offer2);

        Map<Long, Offer>queriedOffers = storage.queryOffers();
        Assert.assertEquals(expected, queriedOffers);

        queriedOffers.put((long) 2, new Offer());

        Map<Long, Offer> AnotherQueriedOffers = storage.queryOffers();
        Assert.assertEquals(expected, AnotherQueriedOffers);
    }

    @Test
    public void queryOffer() {
        Offer expected = new Offer();
        long id = storage.addOffer(expected);
        Offer actual = storage.queryOffer(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryOfferMultipleTimesShouldReturnSameOffer() {
        Offer expected = new Offer();
        long id = storage.addOffer(expected);

        Offer actual = storage.queryOffer(id);
        Assert.assertEquals(expected, actual);

        actual = storage.queryOffer(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void queryNonExistId() {
        storage.addOffer(new Offer());

        // There are only 1 offer in the storage, try to query with id > 1
        Offer actual = storage.queryOffer(10);
        Assert.assertEquals(null, actual);
    }

    @Test
    public void deleteOffer() {
        Offer offer = new Offer();
        long id = storage.addOffer(offer);

        Offer deletedOffer = storage.deleteOffer(id);
        Assert.assertEquals(offer, deletedOffer);
    }

    @Test
    public void deleteNonExistOffer() {
        Offer offer = new Offer();
        storage.addOffer(new Offer());

        // There are only 1 offer in the storage, try to delete with id > 1

        Offer deletedOffer = storage.deleteOffer(10);
        Assert.assertEquals(null, deletedOffer);
    }

    @Test
    public void deleteSameIdTwice() {
        Offer offer = new Offer();
        long id = storage.addOffer(offer);

        Offer deletedOffer = storage.deleteOffer(id);
        Assert.assertEquals(offer, deletedOffer);

        Offer secondDeletedOffer = storage.deleteOffer(id);
        Assert.assertEquals(null, secondDeletedOffer);
    }

    @Test
    public void addOfferAfterDeleteOffer() {
        storage.addOffer(new Offer());
        storage.deleteOffer(0);
        long id = storage.addOffer(new Offer());
        Assert.assertEquals(1, id);
    }

    @Test
    public void deleteAllOffer() {
        Map<Long, Offer> expected = new HashMap<>();
        Offer offer1 = new Offer();
        Offer offer2 = new Offer();
        expected.put((long) 0, offer1);
        expected.put((long) 1, offer2);

        storage.addOffer(offer1);
        storage.addOffer(offer2);

        Map<Long, Offer> actual = storage.deleteOffer();
        Assert.assertEquals(expected, actual);

    }
}
