package com.mycompany.aem.mycustomproject.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageListingModel {

    @ScriptVariable
    private Page currentPage;

    public String getTitle() {
        return currentPage != null ? currentPage.getTitle() : "";
    }

    public List<Page> getChildPages() {
        List<Page> children = new ArrayList<>();
        if (currentPage != null) {
            Iterator<Page> iterator = currentPage.listChildren();
            while (iterator.hasNext()) {
                children.add(iterator.next());
            }
        }
        return children;
    }
}
