package com.alihn.gitcommit;

import android.support.annotation.NonNull;

import com.alihn.gitcommit.listing.ListingComponent;
import com.alihn.gitcommit.listing.ListingModule;
import com.alihn.gitcommit.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class})
public interface AppComponent
{
    @NonNull
    ListingComponent plus(ListingModule listingModule);
}
