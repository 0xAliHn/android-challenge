package com.alihn.gitcommit.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.alihn.gitcommit.CommitRes;
import com.alihn.gitcommit.Constants;
import com.alihn.gitcommit.R;

public class CommitDetailsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_details);

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Constants.COMMITDETAILS))
            {
                CommitRes commitDetails = extras.getParcelable(Constants.COMMITDETAILS);
                if (commitDetails != null)
                {
                    CommitDetailsFragment commitDetailsFragment = CommitDetailsFragment.getInstance(commitDetails);
                    getSupportFragmentManager().beginTransaction().add(R.id.commit_details_container, commitDetailsFragment).commit();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
