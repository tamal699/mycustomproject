package com.mycompany.aem.mycustomproject.core.services;

import org.apache.sling.api.resource.ResourceResolver;
import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Map<String, String>> searchPages(ResourceResolver resolver, String query);
}
