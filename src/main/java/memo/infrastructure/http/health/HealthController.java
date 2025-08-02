package memo.infrastructure.http.health;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthController {
    // @Value("${spring.application.name}")
    // private String serviceName;

    @GET
    public Response health() {
        return Response.ok(new HealthResponse("serviceName", "ok")).build();
    }
}
