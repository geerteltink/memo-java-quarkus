package memo.application.findmemo;

import memo.application.MemoResponse;
import memo.domain.Memo;
import memo.domain.MemoNotFound;
import memo.domain.MemoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class FindMemoHandler {
    @Inject
    private MemoRepository repository;

    public MemoResponse handle(@Valid FindMemoQuery query) {
        Memo memo = this.repository.findById(query.id());

        if (memo.deleted() != null) {
            throw new MemoNotFound(query.id());
        }

        return MemoResponse.from(memo);
    }
}
