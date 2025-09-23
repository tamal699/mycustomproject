package com.mycompany.aem.mycustomproject.core.models;

import com.mycompany.aem.mycustomproject.core.models.ProductBean;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FeaturedProductsModel {

    @Inject
    private List<ProductBean> products;

    @Inject
    private String newtitle;

    public List<ProductBean> getProducts() {
        return products;
    }

    public String getNewtitle(){
        return newtitle + "hello world";
    }
}
