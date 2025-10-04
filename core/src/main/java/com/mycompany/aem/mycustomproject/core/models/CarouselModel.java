package com.mycompany.aem.mycustomproject.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselModel {


    @Inject
    List<Slide> carouselSlides;

    public List<Slide> getCarouselSlides() {
        return carouselSlides;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class Slide {

        @Inject
        @ValueMapValue
        private String image;

        @Inject
        @ValueMapValue
        private String title;

        @Inject
        @ValueMapValue
        private String description;

        @Inject
        @ValueMapValue
        private String link;

        public String getImage() { return image; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getLink() { return link; }
    }
}