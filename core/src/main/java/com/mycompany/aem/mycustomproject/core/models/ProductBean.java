package com.mycompany.aem.mycustomproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductBean {

    @Inject
    private String title;
    @Inject
    private String description;
    @Inject
    private String price;


    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
}
