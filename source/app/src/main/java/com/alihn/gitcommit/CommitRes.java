
package com.alihn.gitcommit;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class CommitRes implements Parcelable
{

    @Json(name = "sha")
    private String sha;
    @Json(name = "commit")
    private Commit commit;
    @Json(name = "author")
    private AuthorDetails author;

    public final static Creator<CommitRes> CREATOR = new Creator<CommitRes>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CommitRes createFromParcel(Parcel in) {
            return new CommitRes(in);
        }

        public CommitRes[] newArray(int size) {
            return (new CommitRes[size]);
        }

    }
    ;

    protected CommitRes(Parcel in) {
        this.sha = ((String) in.readValue((String.class.getClassLoader())));
        this.commit = ((Commit) in.readValue((Commit.class.getClassLoader())));
        this.author = ((AuthorDetails) in.readValue((AuthorDetails.class.getClassLoader())));
    }

    public CommitRes() {
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public CommitRes withSha(String sha) {
        this.sha = sha;
        return this;
    }


    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public CommitRes withCommit(Commit commit) {
        this.commit = commit;
        return this;
    }


    public AuthorDetails getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDetails author) {
        this.author = author;
    }

    public CommitRes withAuthor(AuthorDetails author) {
        this.author = author;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sha);
        dest.writeValue(commit);
        dest.writeValue(author);
    }

    public int describeContents() {
        return  0;
    }

}
