package org.inikilipa.apps.impl.services;

import org.inikilipa.apps.api.model.DataRequest;
import org.inikilipa.apps.api.model.DataRequestType;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class SettlementDayServicesTest {

    @Test
    public void testChooseSettlementDayCase01() throws Exception {
        testBody("Mon Jan 04 00:00:00 EET 2016",
                new DataRequest(
                        "foo",
                        DataRequestType.B,
                        new BigDecimal("0.50"),
                        "SGD",
                        "01 Jan 2016",
                        "02 Jan 2016",
                        200,
                        new BigDecimal("100.25")
                ));
    }

    @Test
    public void testChooseSettlementDayCase02() throws Exception {
        testBody("Mon Jan 04 00:00:00 EET 2016",
                new DataRequest(
                        "foo",
                        DataRequestType.B,
                        new BigDecimal("0.50"),
                        "SGD",
                        "01 Jan 2016",
                        "03 Jan 2016",
                        200,
                        new BigDecimal("100.25")
                ));
    }

    @Test
    public void testChooseSettlementDayCase03() throws Exception {
        testBody("Sun Jan 03 00:00:00 EET 2016",
                new DataRequest(
                        "foo",
                        DataRequestType.B,
                        new BigDecimal("0.50"),
                        "AED",
                        "01 Jan 2016",
                        "02 Jan 2016",
                        200,
                        new BigDecimal("100.25")
                ));
    }

    @Test
    public void testChooseSettlementDayCase04() throws Exception {
        testBody("Sun Jan 03 00:00:00 EET 2016",
                new DataRequest(
                        "foo",
                        DataRequestType.B,
                        new BigDecimal("0.50"),
                        "AED",
                        "01 Jan 2016",
                        "02 Jan 2016",
                        200,
                        new BigDecimal("100.25")
                ));
    }

    private void testBody(String expectedDay, DataRequest request) {
        SettlementDayServices settlementDayServices = new SettlementDayServices();

        Currency currency = request.getCurrency();
        Date settlementDay = request.getSettlementDate();

        Date day = settlementDayServices.chooseSettlementDay(currency, settlementDay);

        Assert.assertEquals(expectedDay, day.toString());
    }
}
