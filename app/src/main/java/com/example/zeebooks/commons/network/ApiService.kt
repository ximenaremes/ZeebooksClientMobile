package com.example.zeebooks.commons.network

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.feature_home.domain.model.*

import okhttp3.MultipartBody
import retrofit2.http.*
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param as Pa


interface ApiService {

    // USERS
    @POST("users/createUser")
    suspend fun createUser(@Body registerRequest: RegisterRequest): User

    @POST("users/login")
    suspend fun login(@Query("email") email: String, @Query("password") password: String): User

    @GET("users/getById/user")
    suspend fun getUserById(@Query("id") userid: String): User

    @GET("users/allUsers")
    suspend fun getAllUsers(): List<User>

    @GET("users/nonAdminUserCount")
    suspend fun getNumberOfUsers(): Int

    @DELETE("users/deleteUser/{id}")
    suspend fun deleteUserById(@Path ("id") id: String): User

    // CATEGORIES
    @GET("category/allCategories")
    suspend fun getCategories(): List<CategoryModel>

    @DELETE("category/deleteCategories/{id}")
    suspend fun deleteCategoryById(@Path ("id") id: String): CategoryModel

    @GET("category/{id}")
    suspend fun getBookByCategoryId(@Path ("id") id: String): List<BookModel>

    @Multipart
    @POST("category/addCategory")
    suspend fun addCategory(  @Query("image") image: MultipartBody.Part,  @Query("name") name:String): CategoryModel

    //BOOKS
    @GET("book/year/{yearOfAppearance}")
    suspend fun getBooksByYear(@Path ("yearOfAppearance") yearOfAppearance : String) : List<BookModel>

    @GET("book/byRating/{ratingb}")
    suspend fun getBooksByRating(@Path ("ratingb") ratingb : String) : List<BookModel>

    @GET("book/{id}")
    suspend fun getBookById(@Path ("id") id: String): BookModel

    @GET("book/name/{name}")
    suspend fun getBooksByName(@Path ("name") name: String): List<BookModel>

    @GET("book/getAllBooks")
    suspend fun getAllBooks(): List<BookModel>

    @GET("book/totalBooksCount")
    suspend fun getTotalBooks(): Int

    @DELETE("book/deleteBook/{id}")
    suspend fun deleteBookById(@Path ("id") id: String): BookModel


    //WISHLIST
    @POST("wishlist/addFavourite/{userId}/{bookId}")
    suspend fun addFavourite(@Path ("userId") userId: String, @Path ("bookId") bookId: String): WishListModel

    @GET("wishlist/favoritesByUser")
    suspend fun getFavoritesByUserId(@Query("userId") userId : String) : List<BookModel>

    @DELETE("wishlist/remove/{userId}/{bookId}")
    suspend fun removeFavourite(@Path ("userId") userId: String, @Path ("bookId") bookId: String)

    //CART AND ORDER

    @POST("cart/add")
    suspend fun addToCart(@Query ("bookId") bookId: String, @Query ("userId") userId: String)

    @POST("cart/addFavourite/{userId}/{bookId}")
    suspend fun addBookToCart(@Path ("userId") userId: String, @Path ("bookId") bookId: String): CartModel

    @GET("cart/favoritesByUser")
    suspend fun getBookToCartByUserId(@Query("userId") userId : String) : List<BookModel>

    @POST("cart/create-order")
    suspend fun addOrder(@Query ("userId") userId: String):String

    @GET("cart/getTotalOrderCount")
    suspend fun getTotalNumberOrder() : Int

    @GET("cart/getAllOrdersById")
    suspend fun getAllOrdersById(@Query ("orderId") orderId: String):OrderBookModel

    @DELETE("cart/removeAll/{userId}")
    suspend fun removeAllCart(@Path ("userId") userId: String)

    @GET("cart/getAllOrders")
    suspend fun getAllOrder(): List<OrderBookModel>


    //FILTER

    @GET("book/allAuthors")
    suspend fun getAllAuthors(): List<String>

    @GET("book/books/author/{nameAuthor}")
    suspend fun getBooksByAuthor(@Path ("price") nameAuthor: String): List<BookModel>
    @GET("book/books/price/{price}")
    suspend fun getBooksByPrice(@Path ("price") price: String): List<BookModel>















}
