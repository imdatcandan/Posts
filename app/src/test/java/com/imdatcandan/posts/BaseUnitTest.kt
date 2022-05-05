package com.imdatcandan.posts

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseUnitTest<T : Any> {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var tested: T

    @Before
    fun setUp() {
        tested = initSelf()
    }

    protected abstract fun initSelf(): T
}