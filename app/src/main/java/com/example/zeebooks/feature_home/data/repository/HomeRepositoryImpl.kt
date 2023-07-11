package com.example.zeebooks.feature_home.data.repository

import com.example.zeebooks.feature_home.data.source.HomeRemoteDataSource
import com.example.zeebooks.feature_home.domain.model.*
import javax.inject.Inject

class HomeRepositoryImpl @Inject internal constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
):HomeRepository {

    override suspend fun getCategories(): Result<List<CategoryModel>> {
        return homeRemoteDataSource.getCategories()
    }

    override suspend fun getBooksByYear(yearOfAppearance: String): Result<List<BookModel>> {
       return homeRemoteDataSource.getBooksByYear(yearOfAppearance)
    }

    override suspend fun getBooksByRating(ratingb: String): Result<List<BookModel>> {
        return homeRemoteDataSource.getBooksByRating(ratingb)
    }

    override suspend fun getBookByCategoryId(id: String): Result<List<BookModel>> {
        return homeRemoteDataSource.getBookByCategoryId(id)
    }

    override suspend fun getBookById(id: String): Result<BookModel> {
        return homeRemoteDataSource.getBookById(id)
    }

    override suspend fun addFavourite(userId: String, bookId: String): Result<WishListModel> {
      return  homeRemoteDataSource.addFavourite(userId,bookId)
    }

    override suspend fun getFavoritesByUserId(userId: String): Result<List<BookModel>> {
        return  homeRemoteDataSource.getFavoritesByUserId(userId)
    }

    override suspend fun removeFavourite(userId: String, bookId: String) {
       return homeRemoteDataSource.removeFavourite(userId,bookId)
    }

    override suspend fun getBooksByName(name: String): Result<List<BookModel>>{
        return  homeRemoteDataSource.getBooksByName(name)
    }

    override suspend fun getAllBooks(): Result<List<BookModel>> {
        return homeRemoteDataSource.getAllBooks()
    }

    override suspend fun deleteBookById(id: String): Result<BookModel> {
       return homeRemoteDataSource.deleteBookById(id)
    }

    override suspend fun addToCart(bookId: String, userId: String) {
        return homeRemoteDataSource.addToCart(bookId,userId)
    }

    override suspend fun addBookToCart(userId: String, bookId: String): Result<CartModel> {
        return homeRemoteDataSource.addBookToCart(userId,bookId)
    }

    override suspend fun getBookToCartByUserId(userId: String): Result<List<BookModel>> {
      return homeRemoteDataSource.getBookToCartByUserId(userId)
    }

    override suspend fun addOrder(userId: String): Result<String> {
        return homeRemoteDataSource.addOrder(userId)
    }

    override suspend fun getTotalNumberOrder(): Result<Int> {
        return homeRemoteDataSource.getTotalNumberOrder()
    }

    override suspend fun getAllOrdersById(orderId: String): Result<OrderBookModel> {
        return homeRemoteDataSource.getAllOrdersById(orderId)
    }

    override suspend fun removeAllCart(userId: String) {
        return homeRemoteDataSource.removeAllCart(userId)
    }

    override suspend fun getAllOrder(): Result<List<OrderBookModel>> {
        return homeRemoteDataSource.getAllOrder()
    }

    override suspend fun getTotalBooks(): Result<Int> {
        return homeRemoteDataSource.getTotalBooks()
    }

    override suspend fun getAllAuthors(): Result<List<String>> {
        return homeRemoteDataSource.getAllAuthors()
    }

    override suspend fun getBooksByPrice(price: String): Result<List<BookModel>> {
        return homeRemoteDataSource.getBooksByPrice(price)
    }

    override suspend fun getBooksByAuthor(nameAuthor: String): Result<List<BookModel>> {
        return homeRemoteDataSource.getBooksByAuthor(nameAuthor)
    }

//    override suspend fun getAllCart(userId: String): Result<List<BookModel>> {
//        return homeRemoteDataSource.getAllCart(userId)
//    }
}