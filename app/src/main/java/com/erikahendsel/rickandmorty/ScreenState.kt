package com.erikahendsel.rickandmorty

//sealed class is a class that cannot be inherited from
//<T> makes a class generic
//A Generic class simply means that the items or functions in that class can be generalized with the parameter(example T) to specify that we can add any type as a parameter in place of T like Integer, Character, String, Double or any other user-defined type.
sealed class ScreenState<T> (val data: T? = null,val message: String? = null){

    class Success<T>(data: T) : ScreenState<T>(data)

    class Loading<T>(data: T? = null) : ScreenState<T>(data)

    class Error<T>(message: String, data: T? = null) : ScreenState<T>(data, message)
}