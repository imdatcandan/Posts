package com.imdatcandan.posts

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
abstract class BaseUnitTest<T : Any> {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var tested: T

    @Before
    @CallSuper
    open fun setUp() {
        tested = initSelf()
    }

    protected abstract fun initSelf(): T

    fun testCoroutine(
        context: CoroutineContext = coroutineTestRule.dispatcher,
        testBody: suspend TestScope.() -> Unit
    ) = runTest(context) { testBody() }
}