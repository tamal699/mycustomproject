package com.mycompany.aem.mycustomproject.core.services.implementation;

import com.mycompany.aem.mycustomproject.core.services.CurrencyConverterService;
import org.osgi.service.component.annotations.Component;

import java.util.Map;

@Component(service = CurrencyConverterService.class)
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private Map<String, Double> rates = Map.of(
            "USD", 1.0,
            "INR", 83.0,
            "EUR", 0.92
    );

    @Override
    public double convert(String source, String target, double amount) {
        if (!rates.containsKey(source) || !rates.containsKey(target)) {
//            throw new RuntimeException("Invalid currency");
        }
        return amount * (rates.get(target) / rates.get(source));
    }

}