package memo.infrastructure.http;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootController {
    @GET
    public Response root() {
        RootResponse response = new RootResponse("hello", "ok");
        return Response.ok(response).build();
    }
}
