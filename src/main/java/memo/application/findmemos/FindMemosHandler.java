package memo.application.findmemos;

import memo.application.MemoResponse;
import memo.domain.Memo;
import memo.domain.MemoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.List;

@ApplicationScoped
public class FindMemosHandler {
    @Inject
    private MemoRepository repository;

    public List<MemoResponse> handle(@Valid FindMemosQuery query) {
        List<Memo> memos = repository.find();

        return memos.stream()
                .map(MemoResponse::from)
                .toList();
    }
}
