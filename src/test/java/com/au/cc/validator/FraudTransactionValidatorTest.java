package com.au.cc.validator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirishdittakavi on 2/10/2016.
 */
public class FraudTransactionValidatorTest {
    private static final String DATE_PATTERN = "uuuu-MM-d'T'HH:mm:ss";
    private  List<String> transactions ;
    private List<String> resultTransactions ;
    private List<String> fraudTransactions;



    @Test
    public void successfulTransactionList() throws Exception {

        transactions = new ArrayList ();
        resultTransactions = new ArrayList( );
        transactions.add( "10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-29T13:15:54, 10.00" );
        transactions.add( "10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-29T13:15:54, 10.00" );
        transactions.add( "10d7ce2f43e35fa57d1bbf8b1e3, 2014-05-29T13:15:54, 10.00" );
        resultTransactions.add( "10d7ce2f43e35fa57d1bbf8b1e2" );

        Assert.assertEquals( resultTransactions,FraudTransactionValidator.fraudValidator(
                transactions,
                LocalDateTime.parse( "2014-05-29T13:15:54",
                        DateTimeFormatter.ofPattern( DATE_PATTERN ) ),
                BigDecimal.valueOf( 11.0 ) ) );

    }
    @Test
    public void failureTransactionList() throws Exception {
        transactions = new ArrayList ();
        resultTransactions = new ArrayList( );

        transactions = new ArrayList ();
        transactions.add(  "10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-29T13:15:54, 10.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-29T13:15:54, 10.00");
        transactions.add("10d7ce2f43e35fa57d1bbf8b1e3, 2014-05-29T13:15:54, 10.00");
        resultTransactions.add( "10d7ce2f43e35fa57d1bbf8b1e2" );
        resultTransactions.add( "10d7ce2f43e35fa57d1bbf8b1e3" );


        Assert.assertEquals( resultTransactions,FraudTransactionValidator.fraudValidator(
                transactions,
                LocalDateTime.parse( "2014-05-29T13:15:54",
                        DateTimeFormatter.ofPattern( DATE_PATTERN ) ),
                BigDecimal.valueOf( 09.0 ) ) );

    }
}