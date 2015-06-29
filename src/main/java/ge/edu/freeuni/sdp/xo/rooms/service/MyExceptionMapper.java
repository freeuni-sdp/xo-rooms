package ge.edu.freeuni.sdp.xo.rooms.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyExceptionMapper  implements
        ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException ex) {
        return Response.status(500).entity(ex.getMessage()).type("text/plain")
                .build();
    }
}