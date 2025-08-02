package memo.application.creatememo;

import memo.application.MemoResponse;
import memo.domain.Memo;
import memo.domain.MemoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class CreateMemoHandler {
    @Inject
    private MemoRepository repository;

    public MemoResponse handle(@Valid CreateMemoCommand command) {
        Memo memo = Memo.create(command.id(), command.content());

        this.repository.insert(memo);

        return MemoResponse.from(memo);
    }
}
