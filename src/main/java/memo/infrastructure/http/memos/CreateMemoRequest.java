package memo.infrastructure.http.memos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMemoRequest(@NotNull UUID id, @NotBlank String content) {

}
