package com.alihn.gitcommit;

import android.app.Application;
import android.os.StrictMode;
import android.support.annotation.Nullable;

import com.alihn.gitcommit.listing.ListingComponent;
import com.alihn.gitcommit.listing.ListingModule;
import com.alihn.gitcommit.network.NetworkModule;


public class BaseApplication extends Application
{
    private AppComponent appComponent;
    @Nullable
    private ListingComponent listingComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        StrictMode.enableDefaults();
        appComponent = createAppComponent();

    }

    private AppComponent createAppComponent()
    {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }


    @Nullable
    public ListingComponent createListingComponent() {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public void releaseListingComponent()
    {
        listingComponent = null;
    }

}
