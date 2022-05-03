package life.league.challenge.kotlin.data.service

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import life.league.challenge.kotlin.data.remote.Api
import life.league.challenge.kotlin.data.remote.RetrofitClient
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ServiceTest {

    private lateinit var service: Service

    private val retrofitClientMock = mockk<RetrofitClient>()
    private val apiMock = mockk<Api>()

    @Before
    fun setup() {
        service = Service(retrofitClientMock)

        every { retrofitClientMock.getClient() } returns apiMock
    }

    @Test
    fun shouldValidateInstanceOfService() {
        val serviceInstance = service.getService()
        assertTrue(serviceInstance is Api)
    }

    @After
    fun clear() {
        clearMocks(retrofitClientMock, apiMock)
    }

}