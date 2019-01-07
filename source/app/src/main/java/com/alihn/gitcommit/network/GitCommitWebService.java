package com.alihn.gitcommit.network;

import android.support.annotation.NonNull;

import com.alihn.gitcommit.CommitRes;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GitCommitWebService {

    @NonNull
    @GET("/repos/android/platform_build/commits")
    Observable<List<CommitRes>> getCommitList(@Query("page") int page);
}
