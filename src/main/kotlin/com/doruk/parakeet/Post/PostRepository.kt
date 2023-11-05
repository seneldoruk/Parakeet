package com.doruk.parakeet.Post

import com.doruk.parakeet.ParakeetUser.ParakeetUser
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostRepository : PagingAndSortingRepository<Post, Long> {
    fun findById(id: Long): Optional<Post>
    fun save(post: Post): Optional<Post>
    fun findAllByUserUsernameOrderByPostDateDesc(username: String, pageable: Pageable): Optional<List<Post>>
    fun findAllByUserUsernameInOrderByPostDateDesc(usernames: List<String>, pageable: Pageable): Optional<List<Post>>
    fun deleteAllByUser(user: ParakeetUser)
}