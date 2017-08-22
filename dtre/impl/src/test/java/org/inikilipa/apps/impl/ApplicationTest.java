package org.inikilipa.apps.impl;

import org.inikilipa.apps.api.model.DataRequest;
import org.inikilipa.apps.api.model.DataRequestType;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ApplicationTest {

    @Test
    public void testPrintReportCase01() throws Exception {
        String expectedReport = new String(Files.readAllBytes(Paths.get("src/test/resources/testPrintReportCase01.txt")));

        Application application = new Application();

        List<DataRequest> dataRequestList = generateDataRequests();
        for (DataRequest dataRequest : dataRequestList) {
            application.accept(dataRequest);
        }

        Assert.assertEquals(expectedReport, application.getReport());
    }

    private List<DataRequest> generateDataRequests() throws Exception {
        List<DataRequest> dataRequestList = new ArrayList<>();

        dataRequestList.add(new DataRequest(
                "foo",
                DataRequestType.B,
                new BigDecimal("0.50"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                200,
                new BigDecimal("100.25")
        ));
        dataRequestList.add(new DataRequest(
                "eeee",
                DataRequestType.S,
                new BigDecimal("0.69"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                50,
                new BigDecimal("53.79")
        ));
        dataRequestList.add(new DataRequest(
                "xxx",
                DataRequestType.B,
                new BigDecimal("1.03"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                557,
                new BigDecimal("3.79")
        ));
        dataRequestList.add(new DataRequest(
                "zzz",
                DataRequestType.S,
                new BigDecimal("0.11"),
                "SGD",
                "01 Jan 2016",
                "02 Jan 2016",
                137,
                new BigDecimal("13.79")
        ));
        dataRequestList.add(new DataRequest(
                "bar",
                DataRequestType.B,
                new BigDecimal("0.22"),
                "AED",
                "05 Jan 2016",
                "08 Jan 2016",
                450,
                new BigDecimal("150.5")
        ));
        dataRequestList.add(new DataRequest(
                "cbbb",
                DataRequestType.S,
                new BigDecimal("0.54"),
                "SAR",
                "05 Jan 2016",
                "09 Jan 2016",
                450,
                new BigDecimal("250.5")
        ));

        return dataRequestList;
    }
}
