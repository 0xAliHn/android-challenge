package com.alihn.gitcommit.listing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.alihn.gitcommit.CommitRes;
import com.alihn.gitcommit.Constants;
import com.alihn.gitcommit.R;
import com.alihn.gitcommit.details.CommitDetailsActivity;

public class CommitListingActivity extends AppCompatActivity implements CommitListingFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.git_commit);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onCommitClicked(CommitRes commitDetails) {
        startCommitDetailsActivity(commitDetails);
    }

    private void startCommitDetailsActivity(CommitRes commitDetails) {
        Intent intent = new Intent(this, CommitDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.COMMITDETAILS, commitDetails);
        intent.putExtras(extras);
        startActivity(intent);
    }

}
