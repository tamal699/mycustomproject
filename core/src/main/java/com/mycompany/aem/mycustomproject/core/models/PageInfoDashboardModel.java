package com.mycompany.aem.mycustomproject.core.models;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageInfoDashboardModel {


    @ScriptVariable
    private Page currentPage;

    @Self
    private SlingHttpServletRequest request;

    private String pageTitle;
    private String pagePath;
    private String pageTemplate;
    private String lastModified;
    private String lastModifiedBy;

    private String pageTitleViaManager;
    private String pagePathViaManager;

    @PostConstruct
    protected void init() {

        if (currentPage != null) {
            pageTitle = currentPage.getTitle();
            pagePath = currentPage.getPath();

            if (currentPage.getTemplate() != null) {
                pageTemplate = currentPage.getTemplate().getTitle();
            }

            Calendar modified = currentPage.getLastModified();
            if (modified != null) {
                lastModified = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(modified.getTime());
            }

            lastModifiedBy = currentPage.getLastModifiedBy();
        }

        // --- pageManager ---
        PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
        if (pageManager != null && currentPage != null) {
            Page pageViaManager = pageManager.getPage(currentPage.getPath());
            if (pageViaManager != null) {
                pageTitleViaManager = pageViaManager.getTitle();
                pagePathViaManager = pageViaManager.getPath();
            }
        }
    }

    // Getters
    public String getPageTitle() { return pageTitle; }
    public String getPagePath() { return pagePath; }
    public String getPageTemplate() { return pageTemplate; }
    public String getLastModified() { return lastModified; }
    public String getLastModifiedBy() { return lastModifiedBy; }
    public String getPageTitleViaManager() { return pageTitleViaManager; }
    public String getPagePathViaManager() { return pagePathViaManager; }
}