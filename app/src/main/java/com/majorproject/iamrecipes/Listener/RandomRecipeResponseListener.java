package com.majorproject.iamrecipes.Listener;

import com.majorproject.iamrecipes.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);


}
