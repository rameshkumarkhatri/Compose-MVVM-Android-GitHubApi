package com.mobifyall.githubapi

import com.mobifyall.githubapi.core.models.SearchResponse
import com.mobifyall.githubapi.core.network.GithubException
import com.mobifyall.githubapi.repos.GithubRepoFake
import com.mobifyall.githubapi.viewmodels.HomeViewModel
import com.mobifyall.githubapi.viewstates.SearchViewState
import com.mobifyall.githubapi.rules.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var repo: GithubRepoFake

    @Before
    fun setUp() {
        repo = GithubRepoFake()
        viewModel = HomeViewModel(repo)
    }

    @Test
    fun `test 1 error UI`() = runTest {

        repo.setData(null, GithubException("there is an error occurred."))
        viewModel.inputSearchTerm("google")
        viewModel.searchOrganization()
        Thread.sleep(1000)
        val result = viewModel.uiState.value
        Assert.assertEquals(
            SearchViewState.Error::class.java,
            result.javaClass
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test 1 No Result UI`() = runTest(UnconfinedTestDispatcher()) {
        repo.setData(data = SearchResponse(0, emptyList()), null)
        viewModel.inputSearchTerm("google")
        viewModel.searchOrganization()
        Thread.sleep(1000)
        val result = viewModel.uiState.value
        Assert.assertEquals(
            SearchViewState.Success::class.java,
            result.javaClass
        )
        Assert.assertEquals((result as SearchViewState.Success).list.size, 0)
    }

    @Test
    fun `test 2 Success Result UI`() = runTest {
        repo.setData(data = SearchResponse(1, FakeData.repoList), null)
        viewModel.inputSearchTerm("google")
        viewModel.searchOrganization()
        Thread.sleep(1000)
        val result = viewModel.uiState.value
        Assert.assertEquals(
            SearchViewState.Success::class.java,
            result.javaClass
        )
        Assert.assertEquals((result as SearchViewState.Success).list.size, FakeData.repoList.size)
    }
}