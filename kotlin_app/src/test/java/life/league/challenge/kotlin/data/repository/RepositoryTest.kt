package life.league.challenge.kotlin.data.repository

import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import life.league.challenge.kotlin.PostsMock
import life.league.challenge.kotlin.data.remote.Api
import life.league.challenge.kotlin.data.service.Service
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    private lateinit var repository: Repository

    private val serviceMock = mockk<Service>()
    private val apiMock = mockk<Api>()

    @Before
    fun setup() {
        repository = Repository(serviceMock)

        every { serviceMock.getService() } returns apiMock
    }

    @Test
    fun shouldReturnAccountApiKeyWhenRepositoryLoginExposeObjectWithSuccess() = runBlocking {
        val accountResponse = PostsMock.getAccount()

        coEvery { apiMock.login() } returns accountResponse

        val currentResponse = repository.login()
        assertEquals(accountResponse, currentResponse)
    }

    @After
    fun clear() {
        clearMocks(serviceMock, apiMock)
    }

}