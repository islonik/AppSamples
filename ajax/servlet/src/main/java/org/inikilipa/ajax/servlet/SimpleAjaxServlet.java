package org.inikilipa.ajax.servlet;

import javax.inject.Inject;
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
 * Created by nikilipa on 7/20/17.
 */
@WebServlet("/SimpleAjaxServlet")
public class SimpleAjaxServlet extends HttpServlet {

    @Inject
    private IdGeneratorService idGeneratorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.TEXT_PLAIN);
        resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        PrintWriter writer = resp.getWriter();
        String prefix = Optional
                .ofNullable(req.getParameter("prefix"))
                .orElse("zero");
        writer.println("<h1>" + idGeneratorService.getPrefixId(prefix) + "</h1>");
        writer.close();
    }

}