package com.mycompany.aem.mycustomproject.core.models;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = org.apache.sling.api.resource.Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CurrencyConverterModel {

    public String getSourceCurrency() {
        return "USD";
    }
    public String getTargetCurrency() {
        return "INR";
    }
    public String getAmount() {
        return "";
    }
    public String getConvertButtonLabel() {
        return "Convert";
    }
    public String getResultLabel() {
        return "Converted Amount:";
    }
}