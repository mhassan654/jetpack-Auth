package com.saavatech.jetpackauthentication.domain.use_case

import com.saavatech.jetpackauthentication.domain.model.FetchPostResult
import com.saavatech.jetpackauthentication.domain.repository.PostRepository

class PostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): FetchPostResult{
        return FetchPostResult(
            result = repository.fetchPosts()
        )
    }

}