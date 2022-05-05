package com.imdatcandan.posts

import com.imdatcandan.posts.data.UserRepositoryImpl
import com.imdatcandan.posts.data.UserService
import com.imdatcandan.posts.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest : BaseUnitTest<UserRepositoryImpl>() {

    private val userService: UserService = mockk(relaxed = true)
    private val user = User(1, "ali", "can")

    override fun initSelf() = UserRepositoryImpl(userService, coroutineTestRule.dispatcher)

    @Test
    fun `should get user detail from service`() = testCoroutine {
        coEvery { userService.getUserDetail(1) } returns user

        val result = tested.getUserDetail(1)

        assertEquals(result, user)
    }
}