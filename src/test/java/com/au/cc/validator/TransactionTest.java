package com.au.cc.validator;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.parse;
import static org.junit.Assert.assertEquals;

/**
 * Created by sirishdittakavi on 2/10/2016.
 */
public class TransactionTest {
    private Transaction test =  Transaction.create("10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-02T13:15:54, 10.00");
    private static final String DATE_PATTERN = "uuuu-MM-d'T'HH:mm:ss";
    @Test
    public void getCreditCardHash() throws Exception {
        assertEquals( "10d7ce2f43e35fa57d1bbf8b1e2", test.getCreditCardHash() );
    }

    @Test
    public void getLocalDate() throws Exception {
        assertEquals( parse("2014-05-02T13:15:54" ,DateTimeFormatter.ofPattern(DATE_PATTERN) ).toLocalDate (), test.getLocalDate () );
    }

    @Test
    public void getPrice() throws Exception {
        assertEquals( new BigDecimal( "10.00" ), test.getPrice() );
    }



}