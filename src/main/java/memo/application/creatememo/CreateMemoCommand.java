package memo.application.creatememo;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CreateMemoCommand(UUID id, @NotBlank String content) {

}
