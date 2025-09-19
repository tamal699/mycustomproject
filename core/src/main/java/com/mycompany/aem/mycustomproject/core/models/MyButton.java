package com.mycompany.aem.mycustomproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


import javax.inject.Inject;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyButton {
@Inject
    private String title;

    public String getTitle() {
        return title+"coming from sling model";
    }
}
