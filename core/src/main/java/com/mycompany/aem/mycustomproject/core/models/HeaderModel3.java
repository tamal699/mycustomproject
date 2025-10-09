package com.mycompany.aem.mycustomproject.core.models;

import com.day.cq.wcm.api.Page;
import com.mycompany.aem.mycustomproject.core.beans.NavigationItem;
import com.mycompany.aem.mycustomproject.core.services.NavigationService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(
        adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeaderModel3 {
    private static final Logger LOG = LoggerFactory.getLogger(HeaderModel3.class);

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue
    private String logo;

    @ValueMapValue
    private String searchIcon;

    @OSGiService
    private NavigationService navigationService;

    private List<NavigationItem> navigationItems;

    private int maxDepth = 2;

    @PostConstruct
    protected void init() {
        try {
            ResourceResolver resolver = request.getResourceResolver();
            Page rootPage = resolver.getResource(currentPage.getPath()).adaptTo(Page.class);
            if (rootPage != null) {
                navigationItems = navigationService.buildNavigation(rootPage, maxDepth);
            } else {
                navigationItems = new ArrayList<>();
                LOG.warn("pg nt fnd: ");
            }
        } catch (Exception e) {
            LOG.error("som errorr: ", e);
            navigationItems = new ArrayList<>();
        }
    }

    public List<NavigationItem> getNavigationItems() {
        return navigationItems;
    }

    public String getLogo() {
        return logo;
    }

    public String getSearchIcon() {
        return searchIcon;
    }
}
