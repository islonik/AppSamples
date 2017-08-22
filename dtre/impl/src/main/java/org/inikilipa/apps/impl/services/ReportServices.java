package org.inikilipa.apps.impl.services;

import org.inikilipa.apps.api.model.DataRequest;
import org.inikilipa.apps.api.model.DataRequestType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * + Amount in USD settled incoming everyday.
 * + Amount in USD settled outgoing everyday.
 * + Ranking of entities based on incoming and outgoing amount.
 * Eg: If entity foo instructs the highest amount for a buy instruction, then foo is rank 1 for outgoing.
 */
public class ReportServices {

    public String generateReport(Map<String, List<DataRequest>> requests) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        int dayColumn = 14; // 14 symbols for the day column
        int incomingColumn = 20; // 20 symbols for the incoming column
        int outgoingColumn = 20; // 20 symbols for the outgoing column
        int rankingColumn = 40; // 40 symbols for the ranking column
        stringBuilder.append("---------------------------------------------------------------------------------------------------\n");
        stringBuilder.append("|     day      |      incoming      |      outgoing      |             ranking by USD             |\n");
        stringBuilder.append("---------------------------------------------------------------------------------------------------\n");
        for (Map.Entry<String, List<DataRequest>> entry : requests.entrySet()) {
            List<DataRequest> dailyRequests = entry.getValue();
            BigDecimal incoming = new BigDecimal("0");
            BigDecimal outgoing = new BigDecimal("0");
            for (DataRequest dataRequest : dailyRequests) {
                BigDecimal theAmountInUsd = dataRequest.getUsdAmount();
                if (dataRequest.getType() == DataRequestType.B) {
                    outgoing = outgoing.add(theAmountInUsd);
                } else {
                    incoming = incoming.add(theAmountInUsd);
                }
            }
            stringBuilder.append("|");
            stringBuilder.append(toSpecificFormat(entry.getKey(), dayColumn));
            stringBuilder.append("|");
            stringBuilder.append(toSpecificFormat(incoming, incomingColumn));
            stringBuilder.append("|");
            stringBuilder.append(toSpecificFormat(outgoing, outgoingColumn));
            stringBuilder.append("|");
            stringBuilder.append(getRanking(dailyRequests, dayColumn, incomingColumn, outgoingColumn, rankingColumn));
            stringBuilder.append("---------------------------------------------------------------------------------------------------\n");
        }
        return stringBuilder.toString();
    }

    private String toSpecificFormat(BigDecimal value, int totalSpace) {
        value = value.setScale(2, RoundingMode.HALF_DOWN);
        String temp = value.toString();
        return toSpecificFormat(temp, totalSpace);
    }

    private String toSpecificFormat(String value, int totalSpace) {
        int tempLength = value.length();
        if (tempLength < totalSpace) {
            StringBuilder adds = new StringBuilder();
            int theAmountOfMissedSpaces = totalSpace - tempLength;
            int half = theAmountOfMissedSpaces / 2;
            for (int i = 0; i < half; i++) {
                adds.append(" ");
            }
            adds.append(value);
            if (half * 2 + 1 == theAmountOfMissedSpaces) {
                half++;
            }
            for (int i = 0; i < half; i++) {
                adds.append(" ");
            }
            value = adds.toString();
        }
        return value;
    }

    private String getRanking(List<DataRequest> dailyRequests, int dayColumn, int incomingColumn, int outgoingColumn, int rankingColumn) {
        String indent = getIndent(dayColumn, incomingColumn, outgoingColumn);
        Collections.sort(dailyRequests);
        int count = 1;
        StringBuilder ranking = new StringBuilder();
        for (int i = 0; i < dailyRequests.size(); i++) {
            StringBuilder row = new StringBuilder();
            if (i != 0) { // the first row already has an indent
                row.append(indent);
            }
            row.append(" ");
            row.append(count++);
            row.append(". ");
            row.append(dailyRequests.get(i).getEntity());
            row.append("; (USD: ");
            row.append(dailyRequests.get(i).getUsdAmount());
            row.append(")");

            if (i == 0) { // the first row already has an indent
                ranking.append(trimColumn(row, rankingColumn));
            } else {
                ranking.append(trimColumn(row, rankingColumn + indent.length()));
            }
            ranking.append("|\n");
        }
        return ranking.toString();
    }

    private String getIndent(int dayColumn, int incomingColumn, int outgoingColumn) {
        StringBuilder indent = new StringBuilder();
        indent.append("|");
        for (int i = 0; i < dayColumn; i++) {
            indent.append(" ");
        }
        indent.append("|");
        for (int i = 0; i < incomingColumn; i++) {
            indent.append(" ");
        }
        indent.append("|");
        for (int i = 0; i < outgoingColumn; i++) {
            indent.append(" ");
        }
        indent.append("|");
        return indent.toString();
    }

    private String trimColumn(StringBuilder value, int rankingColumn) {
        int rowLength = value.length();
        if (rowLength < rankingColumn) {
            int add = rankingColumn - rowLength;
            for (int i = 0; i < add; i++) {
                value.append(" ");
            }
        }
        return value.toString();
    }
}
