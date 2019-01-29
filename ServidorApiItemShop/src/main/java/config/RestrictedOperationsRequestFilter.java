/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import model.Alumno;

@Provider
@PreMatching
public class RestrictedOperationsRequestFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest webRequest;

    @Override

    public void filter(ContainerRequestContext ctx) throws IOException {

        System.out.println(ctx.getUriInfo().getPath());
        if (ctx.getUriInfo().getPath().startsWith("alumno") && !"KK".equals(ctx.getHeaderString("API_KEY"))) {

            Alumno alumno = new Alumno();
            alumno.setId(58);
            ctx.setProperty("key", alumno);
            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Cannot access")
                    .build());
        }
    }

}
