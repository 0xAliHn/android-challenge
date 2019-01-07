package com.alihn.gitcommit.listing;

import android.support.annotation.NonNull;

import com.alihn.gitcommit.network.GitCommitWebService;

import dagger.Module;
import dagger.Provides;


@Module
public class ListingModule {
    @NonNull
    @Provides
    @ListingScope
    CommitListingInteractor provideCommitListingInteractor(GitCommitWebService gitCommitWebService) {
        return new CommitListingInteractorImpl(gitCommitWebService);
    }

    @NonNull
    @Provides
    @ListingScope
    CommitListingPresenter provideCommitListingPresenter(CommitListingInteractor interactor) {
        return new CommitListingPresenterImpl(interactor);
    }
}
