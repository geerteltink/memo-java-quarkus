package memo.application.deletememo;

import memo.domain.Memo;
import memo.domain.MemoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class DeleteMemoHandler {
    @Inject
    private MemoRepository repository;

    public void handle(@Valid DeleteMemoCommand command) {
        Memo memo = this.repository.findById(command.id());

        memo.delete(null);

        this.repository.update(memo);
    }
}
