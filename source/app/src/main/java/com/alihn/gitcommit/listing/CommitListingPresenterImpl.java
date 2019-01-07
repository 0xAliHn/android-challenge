package com.alihn.gitcommit.listing;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alihn.gitcommit.CommitRes;
import com.alihn.gitcommit.util.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class CommitListingPresenterImpl implements CommitListingPresenter {
    @Nullable
    private CommitListingView view;
    private CommitListingInteractor commitListingInteractor;
    private Disposable fetchSubscription;
    private int currentPage = 1;
    @NonNull
    private List<CommitRes> loadedItems = new ArrayList<>();

    CommitListingPresenterImpl(CommitListingInteractor interactor) {
        commitListingInteractor = interactor;
    }

    @Override
    public void setView(CommitListingView view) {
        this.view = view;
        displayCommits();
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }

    private void displayCommits() {
        fetchSubscription = commitListingInteractor.getCommitList(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCommitFetchSuccess, this::onCommitFetchFailed);
    }

    @Override
    public void nextPage() {
            currentPage++;
        displayCommits();
    }

    private void onCommitFetchSuccess(@NonNull List<CommitRes> items) {
        if (!loadedItems.isEmpty()) {
            loadedItems.addAll(items);
        } else {
            loadedItems = new ArrayList<>(items);
        }
        if (isViewAttached()) {
            view.showCommits(loadedItems);
        }
    }

    private void onCommitFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
