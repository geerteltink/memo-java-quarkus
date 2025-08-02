package memo.application.updatememo;

import memo.application.MemoResponse;
import memo.domain.Memo;
import memo.domain.MemoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.time.Instant;

@ApplicationScoped
public class UpdateMemoHandler {
    @Inject
    private MemoRepository repository;

    public MemoResponse handle(@Valid UpdateMemoCommand command) {
        Memo memo = this.repository.findById(command.id());
        memo.update(command.content(), Instant.now());

        this.repository.update(memo);

        return MemoResponse.from(memo);
    }
}
