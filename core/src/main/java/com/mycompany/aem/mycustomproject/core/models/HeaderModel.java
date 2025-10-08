package com.mycompany.aem.mycustomproject.core.models;

import com.mycompany.aem.mycustomproject.core.beans.NavigationItem;
import com.mycompany.aem.mycustomproject.core.services.NavigationService;
import com.day.cq.wcm.api.Page;
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
import java.util.Collections;
import java.util.List;

@Model(
        adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeaderModel {

    private static final Logger LOG = LoggerFactory.getLogger(HeaderModel.class);

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Page currentPage;

    @OSGiService
    private NavigationService navigationService;

    @ValueMapValue
    private String logo;

    @ValueMapValue
    private String searchIcon;

    private List<NavigationItem> navigationItems;

    @PostConstruct
    protected void init() {
        try {
            if (navigationService != null && currentPage != null) {
                Page rootPage = findMainPage(currentPage);

                if (rootPage != null) {
                    navigationItems = navigationService.buildNavigation(rootPage, 2);
                    LOG.debug("Navigation built successfully from {}", rootPage.getPath());
                } else {
                    LOG.warn("Mainpage not found for current page: {}", currentPage.getPath());
                    navigationItems = Collections.emptyList();
                }

            } else {
                navigationItems = Collections.emptyList();
                LOG.warn("NavigationService or CurrentPage is null in HeaderModel");
            }
        } catch (Exception e) {
            LOG.error("Error initializing HeaderModel: ", e);
            navigationItems = Collections.emptyList();
        }
    }

    /**
     * Walk up the tree to find "mainpage" as root.
     */
    private Page findMainPage(Page page) {
        Page parent = page;
        while (parent != null) {
            if ("main-page".equalsIgnoreCase(parent.getName())) {
                return parent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    public String getLogo() {
        return logo;
    }

    public String getSearchIcon() {
        return searchIcon;
    }

    public List<NavigationItem> getNavigationItems() {
        return navigationItems;
    }
}
