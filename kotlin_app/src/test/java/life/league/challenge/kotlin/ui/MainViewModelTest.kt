package life.league.challenge.kotlin.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import life.league.challenge.kotlin.PostsMock
import life.league.challenge.kotlin.data.repository.Repository
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: MainViewModel

    private val repositoryMock = mockk<Repository>()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        viewModel = MainViewModel(repositoryMock)
    }

    @Test
    fun shouldReturnAccountApiKeyWhenViewModelLoginExposeViewStateWithSuccess() {
        val accountResponse = PostsMock.getAccount()
        val expectedViewStateResponse = MainViewState.LoginSuccess(accountResponse.apiKey!!)

        coEvery { repositoryMock.login() } returns accountResponse

        viewModel.login()

        assertEquals(expectedViewStateResponse, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnErrorMessageWhenViewModelLoginExposeViewStateWithError() {
        val errorResponse = Throwable("Unknown error")
        val expectedViewStateResponse = MainViewState.Error(errorResponse.message, errorResponse)

        coEvery { repositoryMock.login() } throws errorResponse

        viewModel.login()

        assertEquals(expectedViewStateResponse, viewModel.viewState.value)
    }

    @Test
    fun shouldValidateIsLoadedAccountWhenViewModelLoginExposeViewStateWithSuccess() {
        shouldReturnAccountApiKeyWhenViewModelLoginExposeViewStateWithSuccess()

        assertFalse(viewModel.isLoadingAccount())
    }

    @Test
    fun shouldValidateIsNotLoadedAccountWhenViewModelLoginExposeViewStateWithError() {
        shouldReturnErrorMessageWhenViewModelLoginExposeViewStateWithError()

        assertTrue(viewModel.isLoadingAccount())
    }

    @Test
    fun shouldReturnPostsListWhenViewModelPostsExposeViewStateWithSuccess() {
        val postsResponse = PostsMock.getPosts()
        val expectedViewStateResponse = MainViewState.PostsSuccess(postsResponse)

        coEvery { repositoryMock.posts(any()) } returns postsResponse

        viewModel.posts(PostsMock.getAccount().apiKey!!)

        assertEquals(expectedViewStateResponse, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnErrorMessageWhenViewModelPostsExposeViewStateWithError() {
        val errorResponse = Throwable("Unknown error")
        val expectedViewStateResponse = MainViewState.Error(errorResponse.message, errorResponse)

        coEvery { repositoryMock.posts(any()) } throws errorResponse

        viewModel.posts(PostsMock.getAccount().apiKey!!)

        assertEquals(expectedViewStateResponse, viewModel.viewState.value)
    }

    @After
    fun clear() {
        Dispatchers.resetMain()

        clearMocks(repositoryMock)
    }

}