package com.majorproject.iamrecipes;

import android.content.Context;

import com.majorproject.iamrecipes.Listener.InstructionListener;
import com.majorproject.iamrecipes.Listener.RandomRecipeResponseListener;
import com.majorproject.iamrecipes.Listener.RecipeDetailsListener;
import com.majorproject.iamrecipes.Listener.SimilarRecipesListener;
import com.majorproject.iamrecipes.Models.InstructionsResponse;
import com.majorproject.iamrecipes.Models.RandomRecipeApiResponse;
import com.majorproject.iamrecipes.Models.RecipesDetailsResponse;
import com.majorproject.iamrecipes.Models.SimilarRecipesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call= callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10", tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }
            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });
    }
    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipesDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipesDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipesDetailsResponse> call, Response<RecipesDetailsResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipesDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });

    }
    public void getSimilarRecipes(SimilarRecipesListener listener, int id){
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipesResponse>> call = callSimilarRecipes.callSimilarRecipes(id, "7", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipesResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipesResponse>> call, Response<List<SimilarRecipesResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }
            @Override
            public void onFailure(Call<List<SimilarRecipesResponse>> call, Throwable t) {
                listener.didError(t.getMessage());


            }
        });
    }

    public void getInstructions(InstructionListener listener, int id){
        Callinstructions callinstructions = retrofit.create(Callinstructions.class);
        Call<List<InstructionsResponse>> call = callinstructions.callInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {

                listener.didError(t.getMessage());

            }
        });
    }

    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")  List<String> tags
        );
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipesDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apikey
        );
    }

    private interface CallSimilarRecipes{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipesResponse>> callSimilarRecipes(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }
    private interface Callinstructions{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>> callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
