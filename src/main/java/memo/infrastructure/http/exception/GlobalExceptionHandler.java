package memo.infrastructure.http.exception;

import memo.domain.MemoNotFound;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.logging.Logger;
import java.util.logging.Level;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        LOGGER.log(Level.INFO, "Handling exception: " + exception.getClass().getSimpleName() + " - " + exception.getMessage());

        if (exception instanceof MemoNotFound) {
            return handleMemoNotFound((MemoNotFound) exception);
        }

        if (exception instanceof NotFoundException) {
            return handleNotFoundException((NotFoundException) exception);
        }

        if (exception instanceof IllegalArgumentException) {
            return handleBadRequest(exception);
        }

        if (exception instanceof ValidationException) {
            return handleValidationException((ValidationException) exception);
        }

        // Log unexpected exceptions at WARNING level
        LOGGER.log(Level.WARNING, "Unexpected exception occurred", exception);

        // Handle any other unexpected exceptions
        return handleInternalServerError(exception);
    }

    private Response handleMemoNotFound(MemoNotFound exception) {
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

    private Response handleNotFoundException(NotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "RESOURCE_NOT_FOUND",
            "The requested resource was not found",
            404
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response handleValidationException(ValidationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "VALIDATION_ERROR",
            String.join(", ", exception.getErrors()),
            400
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response handleBadRequest(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "BAD_REQUEST",
            exception.getMessage(),
            400
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response handleInternalServerError(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "INTERNAL_SERVER_ERROR",
            "An unexpected error occurred",
            500
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
