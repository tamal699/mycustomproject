package com.mycompany.aem.mycustomproject.core.beans;

import com.mycompany.aem.mycustomproject.core.models.LinkGroup;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterBean {

    @ValueMapValue
    private String logoImage;

    @ValueMapValue
    private String logoLink;

    @ValueMapValue
    private String logoDesc;


    public String getLogoImage() {
        return logoImage;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public String getLogoDesc() {
        return logoDesc;
    }
}
