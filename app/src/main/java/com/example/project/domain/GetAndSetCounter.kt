package com.example.project.domain

import com.example.project.domain.models.Counter
import com.example.project.data.CounterRepository
import timber.log.Timber
import javax.inject.Inject

class GetAndSetCounter
@Inject constructor(
    private val preferences: CounterRepository
){

    fun get() = preferences.getValue()

    suspend fun editCounter(counter: Counter) : Result<Counter> {
        if (counter.value < 0) {
            Timber.w(IncrementError(), "Value given was ${counter.value}")
            return Result.failure(IncrementError())
        }
        preferences.setValue(counter)
        return Result.success(counter)
    }

    class IncrementError :
        Exception("Value given to .editCounter() is negative. Negative numbers are not allowed")
}