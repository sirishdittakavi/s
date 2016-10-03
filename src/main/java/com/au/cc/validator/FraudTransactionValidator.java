package com.au.cc.validator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sirishdittakavi on 2/10/2016.
 */
public class FraudTransactionValidator {


    public static List<String> fraudValidator(List<String> transactions, LocalDateTime date, BigDecimal threshold)

    {
        return transactions.stream()
                .map(Transaction::create)
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Transaction::getCreditCardHash))
                .entrySet()
                .parallelStream()
                .filter(e -> {
                    BigDecimal total = e.getValue().stream().map(Transaction::getPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
                    return total.compareTo(threshold) > 0;
                })
                .map(e -> e.getKey())
                .collect(Collectors.toList());


    }
}
