package com.mycompany.aem.mycustomproject.core.models;

import com.mycompany.aem.mycustomproject.core.services.MessageService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ScenicHighlightsModel {

    @OSGiService
    private  MessageService messageService;

    private String message;

    @PostConstruct
    protected void init(){
        if (messageService != null) {
            message = messageService.getMessage();
        }
    }

    @Inject
    private List<Scene> scenes;


    public List<Scene> getScenes() {
        return scenes;
    }


    public String getDisplayMessage() {
        return message;
    }

}
