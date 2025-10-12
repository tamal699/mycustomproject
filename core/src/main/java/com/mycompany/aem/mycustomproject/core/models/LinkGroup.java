package com.mycompany.aem.mycustomproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkGroup {

    @ValueMapValue
    private String title;

    @ChildResource(name = "links/item")
    private List<LinkItem> links;


    public String getTitle() {
        return title;
    }

    public List<LinkItem> getLinks() {
        return links;
    }
}
