package memo.infrastructure.http.exception;

import memo.domain.MemoNotFound;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MemoNotFoundExceptionMapper implements ExceptionMapper<MemoNotFound> {

    @Override
    public Response toResponse(MemoNotFound exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "MEMO_NOT_FOUND",
            exception.getMessage(),
            404
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
