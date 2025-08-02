package memo.infrastructure.http.exception;

import memo.domain.MemoFound;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MemoFoundExceptionMapper implements ExceptionMapper<MemoFound> {

    @Override
    public Response toResponse(MemoFound exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "MEMO_FOUND",
            exception.getMessage(),
            Response.Status.CONFLICT.getStatusCode()
        );

        return Response.status(Response.Status.CONFLICT)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
