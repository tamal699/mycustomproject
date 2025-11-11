package com.mycompany.aem.mycustomproject.core.models;

import com.day.cq.wcm.api.Page;
import com.mycompany.aem.mycustomproject.core.services.SearchService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
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
    private SearchService searchService;

    private List<Map<String, String>> searchResults = new ArrayList<>();

    @PostConstruct
    protected void init() {
        LOG.info("HeaderModel initialized for page: {}",
                currentPage != null ? currentPage.getPath() : "unknown");
    }

    public void executeSearch(String term) {
        this.searchResults = searchService.searchPages(request.getResourceResolver(), term);
    }

    public List<Map<String, String>> getSearchResults() {
        return searchResults;
    }

    public String getLogo() { return logo; }

    public String getSearchIcon() { return searchIcon; }
}
