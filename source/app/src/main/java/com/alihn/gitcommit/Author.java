
package com.alihn.gitcommit;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Author implements Parcelable
{

    @Json(name = "name")
    private String name;
    @Json(name = "email")
    private String email;
    @Json(name = "date")
    private String date;
    public final static Creator<Author> CREATOR = new Creator<Author>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        public Author[] newArray(int size) {
            return (new Author[size]);
        }

    }
    ;

    protected Author(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Author withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Author withDate(String date) {
        this.date = date;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(date);
    }

    public int describeContents() {
        return  0;
    }

}
