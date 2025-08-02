package memo.application.updatememo;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record UpdateMemoCommand(UUID id, @NotBlank String content) {

}
