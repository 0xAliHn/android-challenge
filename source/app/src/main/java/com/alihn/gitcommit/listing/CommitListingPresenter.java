package com.alihn.gitcommit.listing;


public interface CommitListingPresenter
{
    void nextPage();

    void setView(CommitListingView view);

    void destroy();
}
