package com.example.zeebooks.feature_home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.model.OrderBookModel
import com.example.zeebooks.feature_home.domain.model.ShoppingCartItemModel
import com.example.zeebooks.feature_home.domain.model.ShoppingCartModel
import com.example.zeebooks.feature_home.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class BookViewModel @Inject constructor(
    private val getAllBooksByYearUseCase: GetAllBooksByYearUseCase,
    private val getAllBooksByRatingUseCase: GetAllBooksByRatingUseCase,
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val addFavouriteBookUseCase: AddFavouriteBookUseCase,
    private val getFavouriteBookUseCase: GetFavouriteBookUseCase,
    private val deleteBookFavouriteUseCase: DeleteBookFavouriteUseCase,
    private val getBookByNameUseCase: GetBookByNameUseCase,
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val deleteBookByIdUseCase: DeleteBookByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val getAllCartUseCase: GetAllCartUseCase,
    private val addCartUseCase: AddCartUseCase,
    private val getBookToCartByUserIdUseCase: GetBookToCartByUserIdUseCase,
    private val addOrderUseCase: AddOrderUseCase,
    private val getTotalNumberOrderUseCase: GetTotalNumberOrderUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase,
    private val deleteAllCartUseCase: DeleteAllCartUseCase,
    private val getAllOrderUseCase: GetAllOrderUseCase,
    private val getTotalBooksUseCase: GetTotalBooksUseCase,
    private val getAllAuthorsUseCase: GetAllAuthorsUseCase,
    private val getBookByPriceUseCase: GetBookByPriceUseCase,
    private val getAllBooksByAuthorUseCase: GetAllBooksByAuthorUseCase

) : BaseViewModel() {
//    private val favoriteBooks = mutableListOf<String>()


    private val _getAllOrder = MutableLiveData<Result<List<OrderBookModel>>>()
    val getAllOrder: MutableLiveData<Result<List<OrderBookModel>>> = _getAllOrder

    private val _getAllAuthor = MutableLiveData<Result<List<BookModel>>>()
    val getAllAuthor: MutableLiveData<Result<List<BookModel>>> = _getAllAuthor

    private val  _addToCart = MutableLiveData<Result<OrderBookModel>>()
    val  addToCart: MutableLiveData<Result<OrderBookModel>> =  _addToCart

    private val _getAllCart = MutableLiveData<Result<List<BookModel>>>()
    val getAllCart: MutableLiveData<Result<List<BookModel>>> = _getAllCart

    private val _booksLiveData = MutableLiveData<Result<List<BookModel>>>()
    val booksLiveData: LiveData<Result<List<BookModel>>> = _booksLiveData

    private val _booksLRating = MutableLiveData<Result<List<BookModel>>>()
    val booksRating: LiveData<Result<List<BookModel>>> = _booksLRating

    private val _booksPrice = MutableLiveData<Result<List<BookModel>>>()
    val booksPrice: LiveData<Result<List<BookModel>>> = _booksPrice

    private val _booksDetails = MutableLiveData<Result<BookModel>>()
    val bookDetails: LiveData<Result<BookModel>> = _booksDetails

    private val _booksFavourite = MutableLiveData<Result<List<BookModel>>>()
    val booksFavourite: LiveData<Result<List<BookModel>>> = _booksFavourite

    private val _booksSearch = MutableLiveData<Result<List<BookModel>>>()
    val booksSearch: LiveData<Result<List<BookModel>>> = _booksSearch


    private val _allBooks = MutableLiveData<Result<List<BookModel>>>()
    val allBooks: LiveData<Result<List<BookModel>>> = _allBooks

    private val _number = MutableLiveData<Int>()
    val number: LiveData<Int> get() = _number

    private val _numberBook = MutableLiveData<Int>()
    val numberBook: LiveData<Int> get() = _numberBook

    init{
//        getAllBooks()
    }
    fun getBooksByAuthor(nameAuthor: String) {
        viewModelScope.launch {
            val result = getAllBooksByAuthorUseCase.getBooksByAuthor(nameAuthor)
            _getAllAuthor.value = result
        }
    }

    fun getAllBooksByYear(yearOfAppearance: String) {
        viewModelScope.launch {
            val result = getAllBooksByYearUseCase.getBooksByYear(yearOfAppearance)
            _booksLiveData.value = result
        }
    }

    fun getBooksByRating(ratingb: String) {
        viewModelScope.launch {
            val result = getAllBooksByRatingUseCase.getBooksByRating(ratingb)
            _booksLRating.value = result
        }
    }

    fun getBooksByPrice(price: String) {
        viewModelScope.launch {
            val result = getBookByPriceUseCase.getBooksByPrice(price)
            _booksPrice.value = result
        }
    }

    fun getBooksById(id: String) {
        viewModelScope.launch {
            val result = getBookByIdUseCase.getBookById(id)
            _booksDetails.value = result
        }
    }


    fun getFavoritesByUserId(userId: String) {
        viewModelScope.launch {
            val result = getFavouriteBookUseCase.getFavoritesByUserId(userId)
            _booksFavourite.value = result
        }
    }

    fun removeFavourite(userId: String, bookId: String) {
        viewModelScope.launch {
            deleteBookFavouriteUseCase.removeFavourite(userId, bookId)
            getFavoritesByUserId(userId)
        }
    }

    fun addFavourite(userId: String, bookId: String) {
        viewModelScope.launch {
            addFavouriteBookUseCase.addFavourite(userId, bookId)
                .fold(
                    onSuccess = {
                        Timber.e("SUCCESS ADDED BOOK $it")
                    },
                    onFailure = {
                        Timber.e("FAILURE ADDED BOOK $it")

                    }

                )
        }

    }

    fun getBooksByName(name: String) {
        viewModelScope.launch {
            val result = getBookByNameUseCase.getBooksByName(name)
            _booksSearch.value = result
        }
    }

    fun getAllBooks() {
        viewModelScope.launch {
            val result = getAllBooksUseCase.getAllBooks()
            _allBooks.value = result
        }
        getTotalBooks()
    }

    fun deleteBookById(id: String) {
        viewModelScope.launch {
            deleteBookByIdUseCase.deleteBookById(id)
            getAllBooks()
            getTotalBooks()
        }
    }

    fun addToCart( bookId: String, userId: String) {
        viewModelScope.launch {
            addToCartUseCase.addToCart(bookId,userId)
//            _addToCart.value = result

        }
    }
//    fun getAllCart(userId: String) {
//        viewModelScope.launch {
//            val result = getAllCartUseCase.getAllCart(userId)
//            _getAllCart.value = result
//        }
//    }

    fun addBookToCart(userId: String, bookId: String) {
        viewModelScope.launch {
            addCartUseCase.addBookToCart(userId, bookId)
                .fold(
                    onSuccess = {
                        Timber.e("SUCCESS ADDED BOOK to cart $it")
                    },
                    onFailure = {
                        Timber.e("FAILURE ADDED BOOK to cart$it")

                    }

                )
        }
    }

    fun getBookToCartByUserId(userId: String) {
        viewModelScope.launch {
            val result = getBookToCartByUserIdUseCase.getBookToCartByUserId(userId)
            _getAllCart.value = result
        }
    }

    fun addOrder(userId: String) {
        viewModelScope.launch {
            addOrderUseCase.addOrder(userId)
        }
    }

    fun getTotalNumberOrder() {
        viewModelScope.launch {
            val result = getTotalNumberOrderUseCase.getTotalNumberOrder()
            if (result.isSuccess) {
                val number = result.getOrNull() ?: 0
                _number.value = number
            } else {
                result.exceptionOrNull()
            }
        }
    }

    fun getAllOrdersById(orderId: String) {
        viewModelScope.launch {
            val result = getOrderByIdUseCase.getAllOrdersById(orderId)
            _addToCart.value = result
        }
    }

    fun removeAllCart(userId: String) {
        viewModelScope.launch {
            deleteAllCartUseCase.removeAllCart(userId)
        }
    }

    fun getAllOrder() {
        viewModelScope.launch {
            val result = getAllOrderUseCase.getAllOrder()
            _getAllOrder.value = result
        }
    }

    fun getTotalBooks() {
        viewModelScope.launch {
            val result = getTotalBooksUseCase.getTotalBooks()
            if (result.isSuccess) {
                val number = result.getOrNull() ?: 0
                _numberBook.value = number
            } else {
                result.exceptionOrNull()
            }
        }
    }

//    fun getAllAuthors() {
//        viewModelScope.launch {
//            val result = getAllAuthorsUseCase.getAllAuthors()
//            _getAllAuthor.value = result
//        }
//    }

    }