package com.doruk.parakeet.parakeetUser

import com.doruk.parakeet.post.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull


@Service
class ParakeetUserService(
    private val userRepository: ParakeetUserRepository,
    private val postRepository: PostRepository
) {
    fun getUser(username: String): ParakeetUser? {
        return userRepository.findById(username).orElse(null)
    }

    fun createUser(user: ParakeetUser): ParakeetUser {
        return userRepository.save(user)
    }

    @Transactional
    fun followUser(currentUsername: String, userToFollowUsername: String) {
        userRepository.findById(userToFollowUsername).getOrNull()?.let { userToFollow ->
            userRepository.findById(currentUsername).getOrNull()?.let { currentUser ->
                currentUser.following.add(userToFollow)
                userToFollow.followers.add(currentUser)
            }
        }
    }

    @Transactional
    fun deleteUser(user: ParakeetUser) {
        user.following = mutableSetOf()
        user.followers = mutableSetOf()
        postRepository.deleteAllByUser(user)
    }
}