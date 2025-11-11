package com.mycompany.aem.mycustomproject.core.services;

public interface CurrencyConverterService {
    double convert(String source, String target, double amount);
}
