package com.alihn.gitcommit.util;


import android.support.annotation.Nullable;

import io.reactivex.disposables.Disposable;


public class RxUtils
{
    public static void unsubscribe(@Nullable Disposable subscription)
    {
        if (subscription != null && !subscription.isDisposed())
        {
            subscription.dispose();
        } // else subscription doesn't exist or already unsubscribed
    }

    public static void unsubscribe(Disposable... subscriptions)
    {
        for (Disposable subscription : subscriptions)
        {
            unsubscribe(subscription);
        }
    }
}
