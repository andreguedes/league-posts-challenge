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
import life.league.challenge.kotlin.data.model.response.AccountResponse
import life.league.challenge.kotlin.data.model.response.PostResponse
import life.league.challenge.kotlin.data.repository.Repository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class PostsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: PostsViewModel

    private val repositoryMock = mockk<Repository>()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        viewModel = PostsViewModel(repositoryMock)
    }

    @Test
    fun shouldReturnAccountApiKeyWhenViewModelLoginExposeViewStateWithSuccess() {
        val accountResponse = PostsMock.getAccount()
        val expectedViewStateResponse = PostsViewState.LoginSuccess(accountResponse.apiKey!!)

        coEvery { repositoryMock.login() } returns Response.success(accountResponse)

        viewModel.login()

        assertEquals(expectedViewStateResponse, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnErrorMessageWhenViewModelLoginExposeViewStateWithError() {
        val mockBody = "{\"message\":\"Unknown error\"}".toResponseBody("application/json".toMediaTypeOrNull())
        val errorResponse = Response.error<AccountResponse>(401, mockBody)
        val expectedViewStateResponse = PostsViewState.Error

        coEvery { repositoryMock.login() } returns errorResponse

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
        val usersResponse = PostsMock.getUsers()
        val postsFromUsersResponse = PostsMock.getPostsFromUsers()

        val expectedViewStateResponse = PostsViewState.PostsSuccess(postsFromUsersResponse)

        coEvery { repositoryMock.posts(any()) } returns Response.success(postsResponse)
        coEvery { repositoryMock.users(any()) } returns Response.success(usersResponse)

        viewModel.postsFromUsers(PostsMock.getAccount().apiKey!!)

        assertEquals(expectedViewStateResponse, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnErrorMessageWhenViewModelPostsExposeViewStateWithError() {
        val mockBody = "{\"message\":\"Unknown error\"}".toResponseBody("application/json".toMediaTypeOrNull())
        val errorResponse = Response.error<List<PostResponse>>(401, mockBody)
        val expectedViewStateResponse = PostsViewState.Error

        coEvery { repositoryMock.posts(any()) } returns errorResponse

        viewModel.postsFromUsers(PostsMock.getAccount().apiKey!!)

        assertEquals(expectedViewStateResponse, viewModel.viewState.value)
    }

    @After
    fun clear() {
        Dispatchers.resetMain()

        clearMocks(repositoryMock)
    }

}