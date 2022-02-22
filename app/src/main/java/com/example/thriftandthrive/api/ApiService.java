package com.example.thriftandthrive.api;

import com.example.thriftandthrive.api.response.AllProductResponse;
import com.example.thriftandthrive.api.response.CategoryResponse;
import com.example.thriftandthrive.api.response.LoginResponse;
import com.example.thriftandthrive.api.response.RegisterResponse;
import com.example.thriftandthrive.api.response.SliderResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("ecommerce/api/v1/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("ecommerce/api/v1/register")
    Call<RegisterResponse> register(@Field("name") String names, @Field("email") String email, @Field("password") String password);

    @GET("ecommerce/api/v1/get-all-products")
    Call<AllProductResponse> getAllProducts();

    @GET("ecommerce/api/v1/get-categories")
    Call<CategoryResponse> getCategories();

    @GET("ecommerce/api/v1/slider")
    Call<SliderResponse> getSliders();

    @GET("ecommerce/api/v1/get-products-by-category")
    Call<AllProductResponse> getProductsByCategory(@Query("c_id") int catID);

    @GET("ecommerce/api/v1/cart")
    Call<AllProductResponse>getMyCart(@Header("Apikey") String apikey);

    @DELETE("ecommerce/api/v1/cart")
    Call<RegisterResponse> deleteFromCart(@Header("Apikey") String apikey, @Query("c_id") int cartID);

}
