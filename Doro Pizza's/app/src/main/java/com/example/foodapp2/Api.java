package com.example.foodapp2;



import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
public interface Api {
    String BASE_URL = "https://gunter-food-api.herokuapp.com/pizza";
    @GET("pizza")
    Call<List<Results>> getsuperHeroes();
}
