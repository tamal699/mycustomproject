package com.mycompany.aem.mycustomproject.core.services.implementation;

import com.day.cq.wcm.api.Page;
import com.mycompany.aem.mycustomproject.core.beans.NavigationItem;
import com.mycompany.aem.mycustomproject.core.services.NavigationService;
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
        if (rootPage == null) {
            LOG.warn("root (main,pg) pg null");
            return new ArrayList<>();
        }
        return getChildrenRecursive(rootPage, 1, maxDepth);
    }

        private List<NavigationItem> getChildrenRecursive(Page parentPage, int currentDepth, int maxDepth) {
        List<NavigationItem> items = new ArrayList<>();

        if (currentDepth > maxDepth) {
            return items;
        }

        Iterator<Page> children = parentPage.listChildren();
        while (children.hasNext()) {
            Page child = children.next();
            List<NavigationItem> subItems = getChildrenRecursive(child, currentDepth + 1, maxDepth);
            NavigationItem navItem = new NavigationItem(child.getTitle(), child.getPath(), subItems);
            items.add(navItem);
        }
        return items;
    }
}
