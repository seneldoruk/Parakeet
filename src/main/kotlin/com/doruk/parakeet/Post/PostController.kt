package com.doruk.parakeet.Post

import com.doruk.parakeet.ParakeetUser.ParakeetUser
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("posts")
class PostController(val postService: PostService) {
    @PostMapping()
    fun createPost(createPostDTO: CreatePostDTO) {
        val currentuser = ParakeetUser("asd", "afasf")
        postService.createPost(createPostDTO, currentuser)
    }


    @GetMapping("{username}/{page}")
    fun getPostsOfUser(@PathVariable username: String, @PathVariable page: Int = 0): List<Post> {
        return postService.getPostsOfUser(username, page)
    }

    @GetMapping()
    fun getPostsForCurrentUser(principal: Principal): List<Post> {
        return postService.getPostsOfFollowedUsers(principal.name)
    }
}