package org.inikilipa.apps.api.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Optional;

public final class DataRequest implements Comparable<DataRequest> {

    public static final String DATE_FORMAT = "dd MMM yyyy";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private final String entity; // A financial entity whose shares are to be bought or sold
    private final DataRequestType type;
    private final BigDecimal agreedFx; // Agreed Fx is the foreign exchange rate with respect to USD that was agreed
    private final Currency currency;
    private final Date instructionDate; // Date on which the instruction was sent to the company by various clients
    private final Date settlementDate; // The date on which the client wished for the instruction to be settled with respect to Instruction Date
    private final Integer units; // Number of shares to be bought or sold
    private final BigDecimal pricePerUnit;

    /**
     * @throws ParseException           if date (instructionDate or settlementDate) is not supported by the format
     * @throws IllegalArgumentException if currency is not a supported ISO 4217 code; if type is null; if agreedFx is null; if pricePerUnit is null; if units < 1;
     */
    public DataRequest(String entity, DataRequestType type, BigDecimal agreedFx, String currency,
                       String instructionDate, String settlementDate, Integer units, BigDecimal pricePerUnit) throws ParseException {
        if (type == null) {
            throw new IllegalArgumentException("DataRequestType is null.");
        }
        if (agreedFx == null) {
            throw new IllegalArgumentException("AgreedFx is null.");
        }
        if (pricePerUnit == null) {
            throw new IllegalArgumentException("PricePerUnit is null.");
        }
        if (units < 1) {
            throw new IllegalArgumentException(String.format("Units (%s) is less than 1.", units));
        }

        this.entity = Optional
                .ofNullable(entity)
                .filter(s -> !s.trim().isEmpty())
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Entity %s is null.", entity))
                );
        this.type = type;
        this.agreedFx = agreedFx;
        this.currency = Currency.getInstance(currency);
        this.instructionDate = dateFormat.parse(instructionDate);
        this.settlementDate = dateFormat.parse(settlementDate);
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    public String getEntity() {
        return entity;
    }

    public DataRequestType getType() {
        return type;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Date getInstructionDate() {
        return new Date(instructionDate.getTime());
    }

    public Date getSettlementDate() {
        return new Date(settlementDate.getTime());
    }

    public Integer getUnits() {
        return units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    // USD amount of a trade = Price per unit * Units * Agreed Fx
    public BigDecimal getUsdAmount() {
        BigDecimal units = new BigDecimal(this.units);
        return units.multiply(this.agreedFx).multiply(this.pricePerUnit);
    }

    @Override
    public int compareTo(DataRequest that) { // ORDER BY DESC
        return that.getUsdAmount().compareTo(this.getUsdAmount());
    }

    @Override
    public String toString() {
        return "DataRequest{" +
                "entity='" + entity + '\'' +
                ", type=" + type +
                ", agreedFx=" + agreedFx +
                ", currency='" + currency + '\'' +
                ", instructionDate=" + instructionDate +
                ", settlementDate=" + settlementDate +
                ", units=" + units +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataRequest that = (DataRequest) o;

        if (entity != null ? !entity.equals(that.entity) : that.entity != null) return false;
        if (type != that.type) return false;
        if (agreedFx != null ? !agreedFx.equals(that.agreedFx) : that.agreedFx != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (instructionDate != null ? !instructionDate.equals(that.instructionDate) : that.instructionDate != null)
            return false;
        if (settlementDate != null ? !settlementDate.equals(that.settlementDate) : that.settlementDate != null)
            return false;
        if (units != null ? !units.equals(that.units) : that.units != null) return false;
        return pricePerUnit != null ? pricePerUnit.equals(that.pricePerUnit) : that.pricePerUnit == null;
    }

    @Override
    public int hashCode() {
        int result = entity != null ? entity.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (agreedFx != null ? agreedFx.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (instructionDate != null ? instructionDate.hashCode() : 0);
        result = 31 * result + (settlementDate != null ? settlementDate.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + (pricePerUnit != null ? pricePerUnit.hashCode() : 0);
        return result;
    }

}
