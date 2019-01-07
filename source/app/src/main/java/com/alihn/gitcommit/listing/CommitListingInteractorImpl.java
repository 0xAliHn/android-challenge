package com.alihn.gitcommit.listing;

import com.alihn.gitcommit.CommitRes;
import com.alihn.gitcommit.network.GitCommitWebService;

import java.util.List;

import io.reactivex.Observable;


class CommitListingInteractorImpl implements CommitListingInteractor {
    private GitCommitWebService gitCommitWebService;

    CommitListingInteractorImpl(GitCommitWebService gitCommitWebService) {
        this.gitCommitWebService = gitCommitWebService;
    }


    @Override
    public Observable<List<CommitRes>> getCommitList(int page) {

        return gitCommitWebService.getCommitList(page);

    }

}
