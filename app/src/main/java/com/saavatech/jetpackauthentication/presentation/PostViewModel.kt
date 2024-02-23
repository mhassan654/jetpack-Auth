package com.saavatech.jetpackauthentication.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.jetpackauthentication.common.ApiState
import com.saavatech.jetpackauthentication.common.UiEvents
import com.saavatech.jetpackauthentication.data.remote.response.Data
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.data.remote.response.SampleData
import com.saavatech.jetpackauthentication.domain.use_case.PostUseCase
import com.saavatech.jetpackauthentication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postUseCase: PostUseCase):ViewModel() {

    private var _postsState = mutableStateOf(PostsState())
    val postsState: State<PostsState> = _postsState

    private var _postsList = mutableStateOf<List<PostsResponse>>(emptyList())
    val postsList: State<List<PostsResponse>> = _postsList

    private var _postsResource: MutableState<Resource<Unit>> = mutableStateOf(Resource.Loading())
    val postsResource: MutableState<Resource<Unit>> = _postsResource

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _posts = MutableStateFlow<List<Data>>(emptyList())
    val posts: StateFlow<List<Data>> = _posts

    init {
        viewModelScope.launch {
            try {
                val response = getPosts()
                _posts.value = response

                Timber.tag("results success").d( "got my data")
            } catch (e: Exception) {
                // Handle errors here, e.g., show an error message
                Timber.tag("results error").d( e.message.toString())
            }

            ///////////////
        }
//            _postsState.value = postsState.value.copy(isLoading = true)

//            val fetchPostResult = getPosts()
//            _postsList.value = ApiState.Success(fetchPostResult)
////            _postsState.value = postsState.value.copy(isLoading = false)
//
//            when (fetchPostResult) {
//
//                is ApiState.Success -> {
//                    // Update the postsResource with Success
//                    _postsList.value = ApiState.Success(fetchPostResult.data)
////                    _postsList.value = fetchPostResult // Assuming fetchPostResult.data contains the list of posts
//                    Timber.tag("results fetched").d( postsResource.value.data.toString())
//                }
//
//
//                is Resource.Error -> {
//                    // Update the postsResource with Error
////                    _postsResource.value = Resource.Error("Error!", fetchPostResult.message)
//                    // You might want to handle this error, for example, show a Snackbar
//                    UiEvents.SnackbarEvent(fetchPostResult.message ?: "Error!")
//                }
//
//
//                is Resource.Loading -> {
//                    // You're probably already setting isLoading in your postsState
//                }
//            }
//        }
    }


    private suspend fun getPosts(): List<Data> {
       return  postUseCase()
    }
}