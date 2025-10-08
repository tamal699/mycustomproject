package com.mycompany.aem.mycustomproject.core.services.implementation;

import com.day.cq.wcm.api.Page;
import com.mycompany.aem.mycustomproject.core.beans.NavigationItem;
import com.mycompany.aem.mycustomproject.core.services.NavigationService;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = NavigationService.class, immediate = true)
public class NavigationServiceImpl implements NavigationService {

    private static final Logger LOG = LoggerFactory.getLogger(NavigationServiceImpl.class);

    @Override
    public List<NavigationItem> buildNavigation(Page rootPage, int maxDepth) {
        List<NavigationItem> navItems = new ArrayList<>();
        if (rootPage == null) {
            LOG.warn("NavigationServiceImpl.buildNavigation called with null rootPage");
            return navItems;
        }

        try {
            Iterator<Page> children = rootPage.listChildren();
            while (children.hasNext()) {
                Page child = children.next();
                try {
                    NavigationItem item = createItemFromPage(child);

                    // Recursively populate children if depth allows
                    if (maxDepth != 0) {
                        populateChildrenRecursive(child, item, maxDepth > 0 ? maxDepth - 1 : -1);
                    }

                    // Skip hidden pages if hideInNav=true
                    if (!item.isHideInNav()) {
                        navItems.add(item);
                    }
                } catch (Exception e) {
                    LOG.error("Error creating navigation item for page {}", child.getPath(), e);
                }
            }
        } catch (Exception e) {
            LOG.error("Unexpected error while building navigation for root page {}", rootPage.getPath(), e);
        }

        return navItems;
    }

    private void populateChildrenRecursive(Page page, NavigationItem parentItem, int remainingDepth) {
        if (page == null || parentItem == null || remainingDepth == 0) {
            return;
        }

        Iterator<Page> children = page.listChildren();
        while (children.hasNext()) {
            Page child = children.next();
            try {
                NavigationItem childItem = createItemFromPage(child);
                if (!childItem.isHideInNav()) {
                    parentItem.addChild(childItem);
                    populateChildrenRecursive(child, childItem, remainingDepth > 0 ? remainingDepth - 1 : -1);
                }
            } catch (Exception e) {
                LOG.warn("Failed to add child navigation item for page {}", child.getPath(), e);
            }
        }
    }

    private NavigationItem createItemFromPage(Page page) {
        NavigationItem item = new NavigationItem();
        item.setTitle(page.getTitle() != null ? page.getTitle() : page.getName());
        item.setPath(page.getPath());

        try {
            Resource contentResource = page.getContentResource();
            if (contentResource != null && contentResource.getValueMap().containsKey("hideInNav")) {
                Object val = contentResource.getValueMap().get("hideInNav");
                if (val instanceof Boolean) {
                    item.setHideInNav((Boolean) val);
                }
            }
        } catch (Exception e) {
            LOG.debug("Unable to read hideInNav for page {}", page.getPath(), e);
        }

        return item;
    }
}
