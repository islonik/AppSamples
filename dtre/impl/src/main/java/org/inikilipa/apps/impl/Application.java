package org.inikilipa.apps.impl;

import org.inikilipa.apps.api.model.DataRequest;
import org.inikilipa.apps.impl.services.ReportServices;
import org.inikilipa.apps.impl.services.SettlementDayServices;

import java.text.SimpleDateFormat;
import java.util.*;

public class Application {

    private static final SettlementDayServices settlementDayServices = new SettlementDayServices();
    private static final ReportServices reportServices = new ReportServices();

    private static final Map<String, List<DataRequest>> requests = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DataRequest.DATE_FORMAT);

    public void accept(DataRequest dataRequest) {
        Date settlementDay = settlementDayServices.chooseSettlementDay(dataRequest);

        String key = dateFormat.format(settlementDay);

        if (requests.containsKey(key)) {
            List<DataRequest> dataRequests = requests.get(key);
            dataRequests.add(dataRequest);
        } else {
            List<DataRequest> dataRequests = new ArrayList<>();
            dataRequests.add(dataRequest);
            requests.put(key, dataRequests);
        }
    }

    /**
     * This method prints something like this.
     * ---------------------------------------------------------------------------------------------------
     * |     day      |      incoming      |      outgoing      |             ranking by USD             |
     * ---------------------------------------------------------------------------------------------------
     * | 04 Jan 2016  |        0.00        |      10025.00      | 1. foo; (USD: 10025.0000)              |
     * ---------------------------------------------------------------------------------------------------
     * | 10 Jan 2016  |      14899.50      |      60871.50      | 1. cbbb; (USD: 60871.500)              |
     * |              |                    |                    | 2. bar; (USD: 14899.500)               |
     * ---------------------------------------------------------------------------------------------------
     */
    public void printReport() {
        System.out.println(getReport());
    }

    public String getReport() {
        return reportServices.generateReport(Collections.unmodifiableMap(requests));
    }

}
