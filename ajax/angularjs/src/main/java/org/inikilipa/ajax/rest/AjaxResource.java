package org.inikilipa.ajax.rest;

import org.inikilipa.ajax.common.services.IdGeneratorService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by nikilipa on 7/21/17.
 */
@Stateless
@Path("ajax")
public class AjaxResource {

    @Inject
    private IdGeneratorService idGeneratorService;

    @POST
    @Path("prefix/{prefix}")
    @Produces({MediaType.APPLICATION_JSON})
    public String postPrefix(
            @Context HttpServletRequest httpRequest,
            @PathParam("prefix") String prefix
    ) {
        return Json.createObjectBuilder().add("id", idGeneratorService.getPrefixId(prefix)).build().toString();
    }

}
