package com.mycompany.aem.mycustomproject.core.models;

import com.adobe.cq.wcm.core.components.models.Image;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Model(
        adaptables = Resource.class,
        adapters = ArticleListModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ArticleListModel {

    @ValueMapValue
    @Default(values = "Untitled Article")
    private String title;

    @ValueMapValue
    private String summary;

    @ValueMapValue
    private String author;

    @ValueMapValue
    private String publishDate;

    @ValueMapValue
    private List<String> tags;

    @ValueMapValue
    private String thumbnailImage;
    @Inject
    @Via("resource")
    private Resource resource;

    @Inject
    @Via("resource")
    private HttpServletRequest request;

    public String getTitle() { return title; }
    public String getSummary() { return summary; }
    public String getAuthor() { return author; }
    public String getPublishDate() { return publishDate; }
    public List<String> getTags() { return tags; }

    public String getThumbnailImageUrl() {
        if (thumbnailImage != null && !thumbnailImage.isEmpty()) {
            return thumbnailImage;
        }
        return null;
    }

}
