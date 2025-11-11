package com.mycompany.aem.mycustomproject.core.servlets;

import com.mycompany.aem.mycustomproject.core.models.HeaderModel3;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.factory.ModelFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/mycustomproject/search",
                "sling.servlet.methods=GET"
        }
)
public class SearchServlet extends SlingAllMethodsServlet {

    @Reference
    private ModelFactory modelFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String query = request.getParameter("q");
        HeaderModel3 headerModel = modelFactory.createModel(request, HeaderModel3.class);
        headerModel.executeSearch(query);

        JSONArray jsonArray = new JSONArray();
        for (var item : headerModel.getSearchResults()) {
            JSONObject json = new JSONObject();
            json.put("title", item.get("title"));
            json.put("path", item.get("path"));
            jsonArray.put(json);
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());
    }
}
