package com.mycompany.aem.mycustomproject.core.services;

import com.day.cq.wcm.api.Page;
import com.mycompany.aem.mycustomproject.core.beans.NavigationItem;
import java.util.List;

public interface NavigationService {
    List<NavigationItem> buildNavigation(Page rootPage, int maxDepth);
}
