package com.mycompany.aem.mycustomproject.core.models;

import com.day.cq.wcm.api.Page;
import com.mycompany.aem.mycustomproject.core.beans.NavigationItem;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.*;

@Model(
        adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeaderModel2 {

    private static final Logger LOG = LoggerFactory.getLogger(HeaderModel2.class);

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue
    private String logo;

    @ValueMapValue
    private String searchIcon;


    private int maxDepth = 2;
    private String myPath = "/content/mycustomproject/us/en/main-page";

    private List<NavigationItem> navigationItems;

    @PostConstruct
    protected void init() {
        try {
            ResourceResolver resolver = request.getResourceResolver();
            Page rootPage = resolver.getResource(myPath).adaptTo(Page.class);

            if (rootPage != null) {
                navigationItems = getItsChildren(rootPage, 1, maxDepth);
            } else {
                navigationItems = new ArrayList<>();
                LOG.warn("Root page not found at + " + myPath);
            }

        } catch (Exception e) {
            LOG.error("som errorr : ", e);
            navigationItems = new ArrayList<>();
        }
    }


    private List<NavigationItem> getItsChildren(Page parentPage, int currentDepth, int maxDepth) {
        List<NavigationItem> items = new ArrayList<>();

        if (currentDepth > maxDepth) {
            return items; // stop recursion
        }

        Iterator<Page> children = parentPage.listChildren();
        while (children.hasNext()) {
            Page child = children.next();

            // Fetch children only if we haven’t reached maxDepth yet
            List<NavigationItem> subItems = getItsChildren(child, currentDepth + 1, maxDepth);
            items.add(new NavigationItem(child.getTitle(), child.getPath(), subItems));
        }
        return items;
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
