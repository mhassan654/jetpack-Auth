package com.saavatech.jetpackauthentication.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.jetpackauthentication.common.UiEvents
import com.saavatech.jetpackauthentication.data.remote.response.PostsDataResponse
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.domain.model.FetchPostResult
import com.saavatech.jetpackauthentication.domain.model.PostsResult
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

    val postsListState: MutableState<List<PostsResponse>> =
        mutableStateOf(emptyList())

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        try {
            viewModelScope.launch {

                val fetchPostResult = postUseCase()

                when (fetchPostResult.result) {
                    is Resource.Success ->
                        postsListState.value = fetchPostResult.result.data?.data ?: emptyList()

                    is Resource.Error -> {
                        UiEvents.SnackbarEvent(fetchPostResult.result.message ?: "Error!")
                    }
                    is Resource.Loading -> {
                        // You're probably already setting isLoading in your postsState
                    }

                    else -> {}
                }
            }
        }catch (e: Exception){
            Timber.tag("error").d(e)
        }

    }



}