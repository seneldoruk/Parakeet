package com.doruk.parakeet.post

import com.doruk.parakeet.parakeetUser.ParakeetUser
import com.doruk.parakeet.parakeetUser.ParakeetUserRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PostService(val postRepository: PostRepository, val userRepository: ParakeetUserRepository) {
    fun createPost(createPostDTO: CreatePostDTO, currentUser: ParakeetUser): Post {
        val parent = createPostDTO.parentId?.let { postRepository.findById(it).orElse(null) }
        return postRepository.save(Post(user = currentUser, text = createPostDTO.text, parent = parent)).get()
    }

    fun getPostsOfUser(username: String, page: Int = 0): List<Post> {
        val pageable = PageRequest.of(page, 10)
        return postRepository.findAllByUserUsernameOrderByPostDateDesc(username, pageable).get()
    }


    @Transactional
    fun getPostsOfFollowedUsers(currentUserUsername: String, page: Int = 0): List<Post> {
        val user = userRepository.findById(currentUserUsername).get()
        val following = user.following.map { it.username }
        return postRepository.findAllByUserUsernameInOrderByPostDateDesc(following, PageRequest.of(page, 10)).get()
    }
}