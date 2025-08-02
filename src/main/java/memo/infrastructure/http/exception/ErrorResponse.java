package memo.infrastructure.http.exception;

public record ErrorResponse(
    String code,
    String message,
    int status
) {
}
