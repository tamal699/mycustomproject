package com.mycompany.aem.mycustomproject.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ResourceInfoDashboardModel {

  @Self
  private SlingHttpServletRequest request;

  @ScriptVariable
  private Page currentPage;

  private List<ChildPageInfo> childResources = new ArrayList<>();

  @PostConstruct
  protected void init() {
    if (currentPage == null)
      return;

    Iterator<Page> children = currentPage.listChildren();
    while (children.hasNext()) {
      Page childPage = children.next();

      ChildPageInfo info = new ChildPageInfo(
          childPage.getTitle(),
          childPage.getPath(),
          (childPage.getTemplate() != null) ? childPage.getTemplate().getPath() : "No template");

      childResources.add(info);
    }
  }

  public List<ChildPageInfo> getChildResources() {
    return childResources;
  }

  /**
   * Inner class to hold info for each child page
   */
  public static class ChildPageInfo {
    private final String title;
    private final String path;
    private final String template;

    public ChildPageInfo(String title, String path, String template) {
      this.title = title;
      this.path = path;
      this.template = template;
    }

    public String getTitle() {
      return title;
    }

    public String getPath() {
      return path;
    }

    public String getTemplate() {
      return template;
    }
  }
}
