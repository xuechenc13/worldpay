package com.worldpay.worldpay.storage;

import com.worldpay.worldpay.entity.Offer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}
