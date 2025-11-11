package com.mycompany.aem.mycustomproject.core.services.implementation;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.mycompany.aem.mycustomproject.core.services.SearchService;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.*;

@Component(service = SearchService.class, immediate = true)
public class SearchServiceImpl implements SearchService {

    private static final Logger LOG = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Reference
    private QueryBuilder queryBuilder;

    @Override
    public List<Map<String, String>> searchPages(ResourceResolver resolver, String queryTerm) {
        List<Map<String, String>> results = new ArrayList<>();

        try {
            if (resolver == null || queryTerm == null || queryTerm.trim().isEmpty()) {
                return results;
            }

            Map<String, String> predicate = new HashMap<>();
            predicate.put("path", "/content/mycustomproject");
            predicate.put("type", "cq:Page");
            predicate.put("fulltext", queryTerm);
            predicate.put("p.limit", "12");

            Query query = queryBuilder.createQuery(PredicateGroup.create(predicate), resolver.adaptTo(Session.class));
            SearchResult searchResult = query.getResult();

            for (Hit hit : searchResult.getHits()) {
                try {
                    Map<String, String> pageData = new HashMap<>();
                    pageData.put("title", hit.getTitle());
                    pageData.put("path", hit.getPath());
                    results.add(pageData);
                } catch (Exception e) {
                    LOG.warn("Skipping hit due to error", e);
                }
            }
        } catch (Exception e) {
            LOG.error("SearchServiceImpl error: ", e);
        }

        return results;
    }
}
