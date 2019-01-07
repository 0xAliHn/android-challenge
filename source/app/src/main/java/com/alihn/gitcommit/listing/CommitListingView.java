package com.alihn.gitcommit.listing;

import com.alihn.gitcommit.CommitRes;

import java.util.List;

interface CommitListingView {
    void showCommits(List<CommitRes> items);

    void loadingFailed(String errorMessage);

    void onCommitClicked(CommitRes commitDetails);
}
