package com.alihn.gitcommit.listing;


import dagger.Subcomponent;

@ListingScope
@Subcomponent(modules = {ListingModule.class})
public interface ListingComponent {
    CommitListingFragment inject(CommitListingFragment fragment);

}
