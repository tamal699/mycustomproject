package com.mycompany.aem.mycustomproject.core.services;

import com.day.cq.wcm.api.Page;
import com.mycompany.aem.mycustomproject.core.beans.NavigationItem;

import java.util.List;

public interface NavigationService {
    /**
     * Builds a list of navigation items from the given root page.
     *
     * @param rootPage starting page
     * @param maxDepth how many levels deep to go (-1 = unlimited)
     * @return list of NavigationItem objects
     */
    List<NavigationItem> buildNavigation(Page rootPage, int maxDepth);
}
