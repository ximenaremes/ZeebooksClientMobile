package com.example.zeebooks.feature_home.data.repository

import com.example.zeebooks.feature_home.domain.model.*
import retrofit2.http.*

interface HomeRepository {

    suspend fun getCategories(): Result<List<CategoryModel>>
    suspend fun getBooksByYear(yearOfAppearance : String) : Result<List<BookModel>>
    suspend fun getBooksByRating( ratingb: String) : Result<List<BookModel>>
    suspend fun getBookByCategoryId( id: String) : Result<List<BookModel>>
    suspend fun getBookById( id: String) : Result<BookModel>
    suspend fun addFavourite(userId: String, bookId: String) : Result<WishListModel>
    suspend fun getFavoritesByUserId(userId: String) : Result<List<BookModel>>
    suspend fun removeFavourite(userId: String, bookId: String)
    suspend fun getBooksByName(name: String): Result<List<BookModel>>
    suspend fun getAllBooks(): Result<List<BookModel>>

    suspend fun deleteBookById(id: String): Result<BookModel>

    suspend fun addToCart(bookId: String, userId: String)
//    suspend fun getAllCart(userId: String): Result<List<BookModel>>


    suspend fun addBookToCart(userId: String, bookId: String) : Result<CartModel>
    suspend fun getBookToCartByUserId(userId : String) : Result<List<BookModel>>
    suspend fun addOrder(userId: String): Result<String>
    suspend fun getTotalNumberOrder() : Result<Int>
    suspend fun getAllOrdersById(orderId: String): Result<OrderBookModel>
    suspend fun removeAllCart(userId: String)
    suspend fun getAllOrder(): Result<List<OrderBookModel>>

    suspend fun getTotalBooks(): Result<Int>
    suspend fun getAllAuthors(): Result<List<String>>

    suspend fun getBooksByPrice(price: String): Result<List<BookModel>>
    suspend fun getBooksByAuthor(nameAuthor: String): Result<List<BookModel>>







}