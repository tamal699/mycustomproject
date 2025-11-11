package com.mycompany.aem.mycustomproject.core.servlets;

import com.mycompany.aem.mycustomproject.core.services.CurrencyConverterService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/currencyConverter")
public class CurrencyConverterServlet extends SlingAllMethodsServlet {

    @Reference
    private CurrencyConverterService conversionService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String source = request.getParameter("source");
        String target = request.getParameter("target");
        String amountStr = request.getParameter("amount");
        response.setContentType("application/json");
        try {
            double amount = Double.parseDouble(amountStr);
            double converted = conversionService.convert(source, target, amount);
            response.getWriter().write("{\"convertedAmount\":\"" + converted + "\"}");
        } catch (Exception e) {
            response.getWriter().write("{\"error\":\"Invalid input\"}");
        }

    }
}