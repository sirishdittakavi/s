package com.au.cc.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.parse;

/**
 * Created by sirishdittakavi on 29/09/2016.
 */
public class Transaction {

    private static final String DATE_PATTERN = "uuuu-MM-d'T'HH:mm:ss";
    private final String creditCardHash;
    private final LocalDate localDate;
    private final BigDecimal price;


    Transaction(String creditCardHash, LocalDate date, BigDecimal price) {

        this.creditCardHash = creditCardHash;
        this.localDate = date;
        this.price = price;
    }

    public static Transaction create(String input) {
        String[] strings = input.split(",");
        return new Transaction(strings[0].trim(), parse(
                strings[1].trim(),
                DateTimeFormatter.ofPattern(DATE_PATTERN)
        ).toLocalDate(), new BigDecimal(strings[2].trim()));
    }

    public String getCreditCardHash() {
        return creditCardHash;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    //  public Key getKey() {
    //      return new Key( this.getCreditCardHash(), this.getTimestamp().toLocalDate() );
    //}
    @Override
    public String toString() {

        return getCreditCardHash() + "-" + getLocalDate().toString() + "-" + getPrice().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) o;
        return other.getCreditCardHash().equals(creditCardHash) && other.getLocalDate().isEqual(localDate);
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((creditCardHash == null) ? 0 : creditCardHash.hashCode());
        result = prime * result + ((localDate == null) ? 0 : localDate.toString().hashCode());

        return result;

    }
    /*public class Key {
        public String creditCardHash;
        public LocalDate date;

        public Key(String creditCardHash, LocalDate date) {
            this.creditCardHash = creditCardHash;
            this.date = date;
        }
*/
//     /*   @Override
//        public boolean equals(Object o) {
//            if (o == null) {
//                return false;
//            }
//            if (!(o instanceof Key)) {
//                return false;
//            }
//            Key other = (Key) o;
//            return other.creditCardHash.equals( creditCardHash ) && other.date.isEqual( date );
//        }
//
//        @Override
//        public int hashCode() {
//
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + ((creditCardHash == null) ? 0 : creditCardHash.hashCode());
//            result = prime * result + ((date == null) ? 0 : date.toString().hashCode());
//
//            return result;
//
//        }


    /*}*/
}

