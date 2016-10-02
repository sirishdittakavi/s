package com.au.cc.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.LocalDateTime.parse;

/**
 * Created by sirishdittakavi on 29/09/2016.
 */
public class Transaction {

    private static final String DATE_PATTERN = "uuuu-MM-d'T'HH:mm:ss";
    private final String creditCardHash;
    private final LocalDateTime timestamp;
    private final BigDecimal price;


    private Transaction(String creditCardHash, LocalDateTime timestamp, BigDecimal price) {

        this.creditCardHash = creditCardHash;
        this.timestamp = timestamp;
        this.price = price;
    }

    public static Transaction create(String input) {
        String[] strings = input.split( "," );
        return new Transaction( strings[0].trim(), parse(
                strings[1].trim(),
                DateTimeFormatter.ofPattern(DATE_PATTERN)
        ), new BigDecimal( strings[2].trim() ) );
    }

    public String getCreditCardHash() {
        return creditCardHash;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Key getKey() {
        return new Key( this.getCreditCardHash(), this.getTimestamp().toLocalDate() );
    }

    public class Key {
        public String creditCardHash;
        public LocalDate date;

        public Key(String creditCardHash, LocalDate date) {
            this.creditCardHash = creditCardHash;
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Key)) {
                return false;
            }
            Key other = (Key) o;
            return other.creditCardHash.equals( creditCardHash ) && other.date.isEqual( date );
        }

        @Override
        public int hashCode() {

            final int prime = 31;
            int result = 1;
            result = prime * result + ((creditCardHash == null) ? 0 : creditCardHash.hashCode());
            result = prime * result + ((date == null) ? 0 : date.toString().hashCode());

            return result;

        }


    }
}

