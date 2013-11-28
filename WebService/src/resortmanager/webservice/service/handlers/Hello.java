package resortmanager.webservice.service.handlers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * DateIOHandler: 11/6/13
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api")
public class Hello {

    @GET
    @Produces("text/plain")
    @Path("/hello/{name}")
    public String hello(@PathParam("name") String name) {
        return "Hello, ".concat(name);
    }

}
