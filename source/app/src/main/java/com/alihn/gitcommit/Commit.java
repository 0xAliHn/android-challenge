
package com.alihn.gitcommit;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Commit implements Parcelable
{

    @Json(name = "author")
    private Author author;
    @Json(name = "message")
    private String message;

    public final static Creator<Commit> CREATOR = new Creator<Commit>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Commit createFromParcel(Parcel in) {
            return new Commit(in);
        }

        public Commit[] newArray(int size) {
            return (new Commit[size]);
        }

    }
    ;

    protected Commit(Parcel in) {
        this.author = ((Author) in.readValue((Author.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Commit() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Commit withAuthor(Author author) {
        this.author = author;
        return this;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Commit withMessage(String message) {
        this.message = message;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(author);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
