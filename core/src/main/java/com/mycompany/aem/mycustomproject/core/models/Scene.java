package com.mycompany.aem.mycustomproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Scene {

    @Inject
    private String placeName;
    @Inject private String description;
    @Inject private String location;
    @ValueMapValue(name = "fileReference")
    private String sceneryImage;

    public String getPlaceName() { return placeName; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getSceneryImage() {
        return sceneryImage;
    }

}
