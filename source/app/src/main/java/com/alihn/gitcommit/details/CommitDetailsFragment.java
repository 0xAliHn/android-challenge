package com.alihn.gitcommit.details;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alihn.gitcommit.CommitRes;
import com.alihn.gitcommit.Constants;
import com.alihn.gitcommit.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommitDetailsFragment extends Fragment implements CommitDetailsView {
    @Nullable
    @BindView(R.id.author_avatar)
    ImageView avatar;

    @Nullable
    @BindView(R.id.author_name)
    TextView name;
    @Nullable
    @BindView(R.id.author_email)
    TextView email;
    @Nullable
    @BindView(R.id.commit_message)
    TextView message;
    @Nullable
    @BindView(R.id.commit_sha)
    TextView sha;



    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;

    @Nullable
    private CommitRes commitDetails;
    private Unbinder unbinder;

    public CommitDetailsFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static CommitDetailsFragment getInstance(@NonNull CommitRes commitDetails) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.COMMITDETAILS, commitDetails);
        CommitDetailsFragment commitDetailsFragment = new CommitDetailsFragment();
        commitDetailsFragment.setArguments(args);
        return commitDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_commit_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            commitDetails = (CommitRes) getArguments().get(Constants.COMMITDETAILS);
        }
            showDetails(commitDetails);
    }

    @Override
    public void showDetails(@NonNull CommitRes commitDetails) {
        Glide.with(getContext()).load(commitDetails.getAuthor() == null ? R.mipmap.ic_launcher : commitDetails.getAuthor().getAvatarUrl()).into(avatar);
        name.setText(commitDetails.getCommit().getAuthor().getName());
        email.setText(commitDetails.getCommit().getAuthor().getEmail());
        message.setText(commitDetails.getCommit().getMessage());
        sha.setText(String.valueOf(commitDetails.getSha()));

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
