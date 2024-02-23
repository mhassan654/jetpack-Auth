package com.saavatech.jetpackauthentication.domain.use_case

import com.saavatech.jetpackauthentication.data.remote.response.Data
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.data.remote.response.SampleData
import com.saavatech.jetpackauthentication.domain.model.FetchPostResult
import com.saavatech.jetpackauthentication.domain.repository.PostRepository
import com.saavatech.jetpackauthentication.util.Resource

class PostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): List<Data> { //: FetchPostResult{
        return    repository.fetchPosts()
    }

}