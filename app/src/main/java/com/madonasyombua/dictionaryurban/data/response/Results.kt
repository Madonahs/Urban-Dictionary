package com.madonasyombua.dictionaryurban.data.response

/**
 * @author madona
 * Like abstract classes, sealed classes allows you to represent constrained hierarchies in which an object can only be of one of the given types.
 * That is, we have a class with a specific number of subclasses.
 * The child classes can be any type of class example(a data class, an object, a regular class, or even another sealed class)
 **/

//Results : Success, Error
sealed class Results<out T : Any> {

    data class Success<out T : Any>(val data: T) : Results<T>()
    data class Error(val exception: Throwable) : Results<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }

}