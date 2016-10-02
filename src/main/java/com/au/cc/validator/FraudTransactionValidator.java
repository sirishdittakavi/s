package com.au.cc.validator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sirishdittakavi on 2/10/2016.
 */
public class FraudTransactionValidator {


    static List<String> fraudValidator(
    List<String> transactions, LocalDateTime
    date,
    BigDecimal threshold)

    {

        List<Transaction> typedTransactions =
                transactions.stream()
                        .map( Transaction::create )
                        .filter( e -> e.getTimestamp().toLocalDate().equals( date.toLocalDate() ) )
                        .collect( Collectors.toList() );

//        System.out.println( "typedTransactions = " + typedTransactions.size() );

        Map<Transaction.Key, List<Transaction>> transactionsByKey =
                typedTransactions.stream()
                        .collect(
                                Collectors.groupingBy( Transaction::getKey )
                        );

    //    System.out.println( "transactionsByKey.size() = " + transactionsByKey.toString() );
        List<String> fradulentCards = transactionsByKey.entrySet()
                .parallelStream()
                .filter( e -> {
                    BigDecimal total = e.getValue().stream().map( Transaction::getPrice ).reduce( BigDecimal.valueOf( 0 ), BigDecimal::add );
                    return total.compareTo( threshold ) >= 0;
                } )
                .map( e -> e.getKey().creditCardHash )
                .collect( Collectors.toList() );
  //      System.out.println( "fradulentCards.size() = " + fradulentCards.size() );
        return fradulentCards;
    }

}
