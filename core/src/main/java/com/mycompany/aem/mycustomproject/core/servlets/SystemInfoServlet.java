package com.mycompany.aem.mycustomproject.core.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/systeminfo"
    }
)
public class SystemInfoServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");

        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String userAgent = request.getHeader("User-Agent");

        String output = String.format(
            "Method: GET\nCurrent Time: %s\nUser Agent: %s\nMessage: GET request received successfully",
            currentTime, userAgent
        );

        response.getWriter().write(output);
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");

        String name = request.getParameter("name");
        String action = request.getParameter("action");

        String output = String.format(
            "Method: POST\nName: %s\nAction: %s\nMessage: POST request processed",
            name != null ? name : "unknown",
            action != null ? action : "none"
        );

        response.getWriter().write(output);
    }
}
