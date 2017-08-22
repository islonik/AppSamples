package org.inikilipa.apps.impl.services;

import org.inikilipa.apps.api.model.DataRequest;

import java.util.*;

/**
 * + A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday. No other holidays to be taken into account.
 * + A trade can only be settled on a working day.
 * + If an instructed settlement date falls on a weekend, then the settlement date should be changed to the next working day.
 */
public class SettlementDayServices {

    private static final String AED = "AED"; // The United Arab Emirates dirham
    private static final String SAR = "SAR"; // The Saudi riyal

    private static final Set<String> sunday2Thursday = Collections.unmodifiableSet(new TreeSet<String>(String.CASE_INSENSITIVE_ORDER) {{
        add(AED);
        add(SAR);
    }});

    public Date chooseSettlementDay(DataRequest request) {
        Currency currency = request.getCurrency();
        Date settlementDay = request.getSettlementDate();
        return chooseSettlementDay(currency, settlementDay);
    }

    public Date chooseSettlementDay(Currency currency, Date settlementDate) {
        Date returnDate = settlementDate;
        int day = getDayOfWeek(settlementDate);

        if (sunday2Thursday.contains(currency.getCurrencyCode())) { // 1 - 5 are okay
            if (day == 6) { // Friday
                returnDate = addTwoDays(settlementDate);
            } else if (day == 7) { // Saturday
                returnDate = addDay(settlementDate);
            }
        } else { // 2 - 6 are okay
            if (day == 1) { // Sunday
                returnDate = addDay(settlementDate);
            } else if (day == 7) { // Saturday
                returnDate = addTwoDays(settlementDate);
            }
        }
        return returnDate;
    }

    // 1 - Sunday
    // 2 - Monday
    // 3 - Tuesday
    // 4 - Wednesday
    // 5 - Thursday
    // 6 - Friday
    // 7 - Saturday
    private int getDayOfWeek(Date settlementDate) {
        Calendar c = Calendar.getInstance(); // is not thread-safe
        c.setTime(settlementDate);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    private Date addDay(Date settlementDate) {
        Calendar c = Calendar.getInstance(); // is not thread-safe
        c.setTime(settlementDate);
        c.add(Calendar.DATE, 1); // add one day
        return c.getTime();
    }

    private Date addTwoDays(Date settlementDate) {
        Calendar c = Calendar.getInstance(); // is not thread-safe
        c.setTime(settlementDate);
        c.add(Calendar.DATE, 2); // add two days
        return c.getTime();
    }

}
