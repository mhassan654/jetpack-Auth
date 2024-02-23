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

//    private var _postsResource: MutableState<Resource<Unit>> = mutableStateOf(Resource.Loading())
//    val postsResource: MutableState<Resource<Unit>> = _postsResource

//    private var _postsResource: MutableState<Resource<List<PostsResponse>>> = mutableStateOf(Resource.Loading())
//    val postsResource: MutableState<Resource<List<PostsResponse>>> = _postsResource

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

//    init {
//        viewModelScope.launch {
//            _postsState.value = postsState.value.copy(isLoading = true)
//
//            val fetchPostResult = getPosts()
//            Timber.tag("posts data").d(fetchPostResult.result?.toString())
//
//            _postsState.value = postsState.value.copy(isLoading = false)
//
//            if (fetchPostResult.result is Resource.Success) {
//                postsList.value = fetchPostResult
//                Timber.tag("results fetched").d("can proceed")
//            }
//
//            else if (fetchPostResult.result is Resource.Error) {
//                UiEvents.SnackbarEvent(
//                    fetchPostResult.result.message ?: "Error!"
//                )
//            }
//        }
//    }

    init {
        viewModelScope.launch {
            _postsState.value = postsState.value.copy(isLoading = true)

            val fetchPostResult = getPosts()

            _postsState.value = postsState.value.copy(isLoading = false)

            when (fetchPostResult) {
                is PostsDataResponse.Success<*> -> {
                    // Update the postsResource with Success
//                    _postsResource.value = Resource.Success(fetchPostResult.data)
                    _postsList.value = fetchPostResult // Assuming fetchPostResult.data contains the list of posts
//                    Timber.tag("results fetched").d( postsResource.value.data.toString())
                }
//                is Resource.Error -> {
//                    // Update the postsResource with Error
////                    _postsResource.value = Resource.Error("Error!", fetchPostResult.message)
//                    // You might want to handle this error, for example, show a Snackbar
//                    UiEvents.SnackbarEvent(fetchPostResult.message ?: "Error!")
//                }
//                is PostsDataResponse.Loading? -> {
//                    // You're probably already setting isLoading in your postsState
//                }

                else -> {}
            }
        }
    }



    private suspend fun getPosts(): List<PostsResponse>? {
       return  postUseCase().data
    }
}