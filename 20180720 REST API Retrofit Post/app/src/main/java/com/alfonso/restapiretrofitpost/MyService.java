package com.alfonso.restapiretrofitpost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyService {

    //POSTS (POST.CLASS)
    @GET("posts/")
    Call<List<Post>> listPosts();

    @GET("posts/{post_id}")
    Call<Post> getPost(@Path("post_id") long post_id);

    @DELETE("posts/{post_id}")
    Call<Void> deletePost(@Path("post_id") long post_id);

    @POST("posts/")
    Call<Post> savePost(@Body Post post);


    //USERS (PERSONA.CLASS)
    @GET("users/{user_id}")
    Call<Persona> getPersona(@Path("user_id") long user_id);


   /* @GET("users/")
    Call<List<Persona>> listPersonas();

    @GET("users/{user_id}")
    Call<Persona> getPersona(@Path("user_id") long user_id);

    @DELETE("users/{user_id}")
    Call<Void> deletePersona(@Path("user_id") long user_id);

    @POST("users/")
    Call<Persona> savePersona(@Body Persona user);*/

}
