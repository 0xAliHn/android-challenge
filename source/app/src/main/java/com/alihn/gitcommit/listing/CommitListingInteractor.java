package com.alihn.gitcommit.listing;

import com.alihn.gitcommit.CommitRes;

import java.util.List;

import io.reactivex.Observable;

public interface CommitListingInteractor {

    Observable<List<CommitRes>> getCommitList(int page);
}
