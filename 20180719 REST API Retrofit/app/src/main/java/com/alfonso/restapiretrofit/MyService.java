package com.alfonso.restapiretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyService {

    @GET("users/")
    Call<List<Persona>> listPersonas();

    @GET("users/{user_id}")
    Call<Persona> getPersona(@Path("user_id") long user_id);

    @DELETE("users/{user_id}")
    Call<Void> deletePersona(@Path("user_id") long user_id);

    @POST("users/")
    Call<Persona> savePersona(@Body Persona user);

}
