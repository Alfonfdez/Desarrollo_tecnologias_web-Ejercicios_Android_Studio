package com.alfonso.restapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {

        @GET("users/")
        Call<List<Persona>> listPersonas();

}
