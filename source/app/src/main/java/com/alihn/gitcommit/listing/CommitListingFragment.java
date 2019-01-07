package com.alihn.gitcommit.listing;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alihn.gitcommit.BaseApplication;
import com.alihn.gitcommit.CommitRes;
import com.alihn.gitcommit.Constants;
import com.alihn.gitcommit.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommitListingFragment extends Fragment implements CommitListingView {
    @Inject
    CommitListingPresenter commitListingPresenter;

    @Nullable
    @BindView(R.id.commit_listing_recyclerview)
    RecyclerView commitListingRecyclerView;

    @Nullable
    private RecyclerView.Adapter adapter;
    @Nullable
    private List<CommitRes> items = new ArrayList<>(30);
    @Nullable
    private Callback callback;
    private Unbinder unbinder;

    public CommitListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).createListingComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_commits, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initLayoutReferences();
        commitListingRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                    commitListingPresenter.nextPage();
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commitListingPresenter.setView(this);
        if (savedInstanceState != null) {
            items = savedInstanceState.getParcelableArrayList(Constants.COMMITDETAILS);
            adapter.notifyDataSetChanged();
            commitListingRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    private void initLayoutReferences() {
        commitListingRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        commitListingRecyclerView.setLayoutManager(layoutManager);
        adapter = new CommitListingAdapter(items, this);
        commitListingRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showCommits(@NonNull List<CommitRes> commitRes) {
        this.items.clear();
        this.items.addAll(commitRes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadingFailed(@NonNull String errorMessage) {
        Snackbar.make(commitListingRecyclerView, errorMessage, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void onCommitClicked(CommitRes commitDetails) {
        callback.onCommitClicked(commitDetails);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        commitListingPresenter.destroy();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseListingComponent();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.COMMITDETAILS, (ArrayList<? extends Parcelable>) items);
    }


    public interface Callback {
        void onCommitClicked(CommitRes commitDetails);
    }


}
