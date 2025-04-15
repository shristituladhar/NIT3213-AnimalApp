package com.example.nit3213app

import com.example.nit3213app.api.ApiService
import com.example.nit3213app.api.LoginRequest
import com.example.nit3213app.api.LoginResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class ApiServiceTest {

    private val apiService = mockk<ApiService>()

    @Test
    fun `login returns keypass successfully`() = runBlocking {
        // Arrange
        val request = LoginRequest("Shristi", "s4678657")
        val expectedResponse = Response.success(LoginResponse("animals"))

        coEvery { apiService.login(request) } returns expectedResponse

        // Act
        val actualResponse = apiService.login(request)

        // Assert
        assertEquals(true, actualResponse.isSuccessful)
        assertEquals("animals", actualResponse.body()?.keypass)
    }
}
