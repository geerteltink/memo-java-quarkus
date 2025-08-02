package memo.infrastructure.http.memos;

import jakarta.validation.constraints.NotBlank;

public record UpdateMemoRequest(@NotBlank String content) {

}
