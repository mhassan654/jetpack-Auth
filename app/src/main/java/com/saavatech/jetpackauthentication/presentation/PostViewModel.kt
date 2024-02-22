package com.saavatech.jetpackauthentication.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.jetpackauthentication.common.UiEvents
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.domain.model.FetchPostResult
import com.saavatech.jetpackauthentication.domain.use_case.PostUseCase
import com.saavatech.jetpackauthentication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postUseCase: PostUseCase):ViewModel() {

    private var _postsState = mutableStateOf(PostsState())
    val postsState: State<PostsState> = _postsState

    private var _postsList: MutableState<List<PostsResponse>> =
        mutableStateOf(emptyList())
    val postsList: MutableState<List<PostsResponse>> = _postsList

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _postsState.value = postsState.value.copy(isLoading = true)

            val fetchPostResult = getPosts()
//            postsList.value = fetchPostResult.result.toString()
            Timber.tag("posts data").d(fetchPostResult.result?.toString())

            _postsState.value = postsState.value.copy(isLoading = false)
//
            if (fetchPostResult.result is Resource.Success) {
                postsList.value = fetchPostResult
                Timber.tag("results fetched").d("can proceed")
            }
//
            else if (fetchPostResult.result is Resource.Error) {
                UiEvents.SnackbarEvent(
                    fetchPostResult.result.message ?: "Error!"
                )
            }
        }
    }


    private suspend fun getPosts(): FetchPostResult {
       return  postUseCase()
    }
}