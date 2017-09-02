package org.inikilipa.ajax.servlet;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.inikilipa.ajax.common.services.IdGeneratorService;

/**
 * Created by nikilipa on 7/21/17.
 */
@WebServlet("/ListAjaxServlet")
public class ListAjaxServlet extends HttpServlet {

    @Inject
    private IdGeneratorService idGeneratorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        PrintWriter writer = resp.getWriter();
        String prefix = Optional
                .ofNullable(req.getParameter("prefix"))
                .orElse("zero");
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (int i = 0; i < 10; i++) {
            jsonArray.add(idGeneratorService.getPrefixId(prefix));
        }
        writer.println(jsonArray.build());
        writer.close();
    }

}
