package com.saavatech.jetpackauthentication.domain.use_case

import com.saavatech.jetpackauthentication.domain.model.PostsResult
import com.saavatech.jetpackauthentication.domain.repository.PostRepository

class PostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): PostsResult {
        return PostsResult(
            result = repository.fetchPosts()
        )
    }

}