package com.majorproject.iamrecipes.Listener;

import com.majorproject.iamrecipes.Models.SimilarRecipesResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipesResponse> response, String message);
    void didError(String message);

}
