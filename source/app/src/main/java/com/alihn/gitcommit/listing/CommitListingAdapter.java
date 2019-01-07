package com.alihn.gitcommit.listing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alihn.gitcommit.AuthorDetails;
import com.alihn.gitcommit.Commit;
import com.alihn.gitcommit.CommitRes;
import com.alihn.gitcommit.GlideApp;
import com.alihn.gitcommit.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitListingAdapter extends RecyclerView.Adapter<CommitListingAdapter.ViewHolder> {
    private List<CommitRes> commitRes;
    private Context context;
    private CommitListingView view;

    CommitListingAdapter(List<CommitRes> commitRes, CommitListingView commitListingView) {
        this.commitRes = commitRes;
        view = commitListingView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.commit_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.commit = commitRes.get(position).getCommit();
        holder.authorDetails = commitRes.get(position).getAuthor();
        holder.message.setText(holder.commit.getMessage());
        holder.author.setText(holder.commit.getAuthor().getName());

        GlideApp.with(context)
                .load(holder.authorDetails == null ? R.mipmap.ic_launcher : holder.authorDetails.getAvatarUrl())
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return commitRes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Commit commit;
        AuthorDetails authorDetails;
        @Nullable
        @BindView(R.id.commit_avatar)
        ImageView avatar;

        @Nullable
        @BindView(R.id.commit_message)
        TextView message;

        @Nullable
        @BindView(R.id.commit_author)
        TextView author;

        ViewHolder(@NonNull View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            CommitListingAdapter.this.view.onCommitClicked(commitRes.get(getAdapterPosition()));
        }
    }
}
