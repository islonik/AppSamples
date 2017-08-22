package org.inikilipa.apps.api.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;

public class DataRequestTest {

    @Test
    public void testConstructCase01() throws Exception {

        DataRequest request = new DataRequest(
                "foo",
                DataRequestType.B,
                new BigDecimal("0.50"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                200,
                new BigDecimal("100.25")
        );

        Assert.assertEquals("foo", request.getEntity());
        Assert.assertEquals(DataRequestType.B, request.getType());
        Assert.assertEquals("0.50", request.getAgreedFx().toString());
        Assert.assertEquals("SGD", request.getCurrency().toString());
        Assert.assertEquals("SGD", request.getCurrency().getCurrencyCode());
        Assert.assertEquals("Fri Jan 01 00:00:00 EET 2016", request.getInstructionDate().toString());
        Assert.assertEquals("Sat Jan 02 00:00:00 EET 2016", request.getSettlementDate().toString());
        Assert.assertEquals("200", request.getUnits().toString());
        Assert.assertEquals("100.25", request.getPricePerUnit().toString());
        Assert.assertEquals("10025.0000", request.getUsdAmount().toString());
        Assert.assertEquals("DataRequest{entity='foo', type=B, agreedFx=0.50, currency='SGD', instructionDate=Fri Jan 01 00:00:00 EET 2016, settlementDate=Sat Jan 02 00:00:00 EET 2016, units=200, pricePerUnit=100.25}",
                request.toString());
    }

    @Test(expected = ParseException.class)
    public void testConstructCase02() throws Exception {
        new DataRequest(
                "foo",
                DataRequestType.B,
                new BigDecimal("0.50"),
                "SGD",
                "01 01 2016",
                "02 Jan 2016",
                200,
                new BigDecimal("100.25")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructCase03() throws Exception {
        new DataRequest(
                "foo",
                DataRequestType.B,
                new BigDecimal("0.50"),
                "SGP",
                "01 Jan 2016",
                "02 Jan 2016",
                200,
                new BigDecimal("100.25")
        );
    }

    @Test(expected = NullPointerException.class)
    public void testConstructCase04() throws Exception {
        new DataRequest(
                "foo",
                DataRequestType.B,
                new BigDecimal("0.50"),
                null,
                "01 Jan 2016",
                "02 Jan 2016",
                200,
                new BigDecimal("100.25")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructCase05() throws Exception {
        new DataRequest(
                "foo",
                null,
                new BigDecimal("0.50"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                200,
                new BigDecimal("100.25")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructCase06() throws Exception {
        new DataRequest(
                "foo",
                DataRequestType.B,
                null,
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                200,
                new BigDecimal("100.25")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructCase07() throws Exception {
        new DataRequest(
                "foo",
                DataRequestType.B,
                new BigDecimal("0.50"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                0,
                new BigDecimal("100.25")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructCase08() throws Exception {
        new DataRequest(
                "foo",
                DataRequestType.B,
                new BigDecimal("0.50"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                1,
                null
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructCase09() throws Exception {
        new DataRequest(
                " ",
                DataRequestType.B,
                new BigDecimal("0.50"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                1,
                new BigDecimal("0.50")
        );
    }
}
