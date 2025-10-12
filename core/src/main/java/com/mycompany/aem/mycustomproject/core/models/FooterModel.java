package com.mycompany.aem.mycustomproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;



@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {

    @ValueMapValue
    private String logoImage;

    @ValueMapValue
    private String logoLink;

    @ValueMapValue
    private String logoDesc;

    @ChildResource(name = "footerLinks/item")
    private List<LinkGroup> footerLinks;


    public String getLogoImage() {
        return logoImage;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public String getLogoDesc() {
        return logoDesc;
    }

    public List<LinkGroup> getFooterLinks() {
        return footerLinks;
    }
}
