package com.example.zeebooks.feature_home.data.source

import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.commons.network.RetrofitClient
import com.example.zeebooks.feature_home.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Path
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getCategories(): Result<List<CategoryModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getCategories()
            }
        }
    }

    suspend fun getBooksByYear(yearOfAppearance : String): Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBooksByYear(yearOfAppearance )
            }
        }
    }

    suspend fun getBooksByRating(ratingb : String): Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBooksByRating(ratingb )
            }
        }
    }
    suspend fun getBookByCategoryId(id : String): Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBookByCategoryId(id )
            }
        }
    }
    suspend fun getBookById(id : String): Result<BookModel>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBookById(id )
            }
        }
    }

    suspend fun addFavourite(userId: String, bookId: String): Result<WishListModel>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.addFavourite(userId,bookId)
            }
        }
    }

    suspend fun getFavoritesByUserId(userId: String): Result<List<BookModel>> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getFavoritesByUserId(userId)
            }
        }
    }

    suspend fun removeFavourite(userId: String,bookId: String){
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.removeFavourite(userId,bookId)
            }
        }
    }

    suspend fun getBooksByName(name: String):Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBooksByName(name)
            }
        }
    }

    suspend fun getAllBooks():Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getAllBooks()
            }
        }
    }

    suspend fun deleteBookById(id:String):Result<BookModel>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.deleteBookById(id)
            }
        }
    }

    suspend fun addToCart(bookId: String, userId: String){
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.addToCart(bookId,userId)
            }
        }
    }

//    suspend fun getAllCart(userId: String) : Result<List<BookModel>>{
//        return withContext(Dispatchers.IO){
//            kotlin.runCatching {
//                RetrofitClient.apiService.getAllCart(userId)
//            }
//        }
//    }

    suspend fun addBookToCart(userId: String, bookId: String): Result<CartModel>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.addBookToCart(userId,bookId)
            }
        }
    }

    suspend fun getBookToCartByUserId(userId: String): Result<List<BookModel>> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBookToCartByUserId(userId)
            }
        }
    }
    suspend fun addOrder(userId: String): Result<String>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.addOrder(userId)
            }
        }
    }

    suspend fun getTotalNumberOrder(): Result<Int> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getTotalNumberOrder()
            }
        }
    }

    suspend fun getAllOrdersById(orderId: String): Result<OrderBookModel> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getAllOrdersById(orderId)
            }
        }
    }

    suspend fun removeAllCart(userId: String) {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.removeAllCart(userId)
            }
        }
    }

    suspend fun getAllOrder():Result<List<OrderBookModel>> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getAllOrder()
            }
        }
    }

    suspend fun getTotalBooks():Result<Int> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getTotalBooks()
            }
        }
    }

    suspend fun getAllAuthors(): Result<List<String>> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getAllAuthors()
            }
        }
    }

    suspend fun getBooksByPrice(price: String): Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBooksByPrice(price)
            }
        }
    }

    suspend fun getBooksByAuthor(nameAuthor: String): Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBooksByAuthor(nameAuthor)
            }
        }
    }





}