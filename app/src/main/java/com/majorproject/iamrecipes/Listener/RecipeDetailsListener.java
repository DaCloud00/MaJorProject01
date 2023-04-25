package com.majorproject.iamrecipes.Listener;

import com.majorproject.iamrecipes.Models.RecipesDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipesDetailsResponse response, String message);
    void didError(String message);
}
