package com.doruk.parakeet.post

import com.doruk.parakeet.parakeetUser.ParakeetUser
import com.doruk.parakeet.parakeetUser.ParakeetUserService
import com.doruk.parakeet.testClasses.TestBase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

class PostServiceTest : TestBase() {

    companion object {
        @Container
        val postgres = PostgreSQLContainer("postgres:14.1-alpine")

        @JvmStatic
        @DynamicPropertySource
        fun configureTestContainerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.jpa.hibernate.ddl-auto") { "create" }
        }
    }


    @Autowired
    lateinit var postService: PostService

    @Autowired
    lateinit var userService: ParakeetUserService

    lateinit var testUser1: ParakeetUser
    lateinit var testUser2: ParakeetUser
    lateinit var testUser3: ParakeetUser

    @BeforeEach
    fun setUp() {
        testUser1 = userService.createUser(ParakeetUser("testuser1", "123"))
        testUser2 = userService.createUser(ParakeetUser("testuser2", "123"))
        testUser3 = userService.createUser(ParakeetUser("testuser3", "123"))
    }

    @AfterEach
    fun deleteAll() {
        userService.deleteUser(testUser1)
        userService.deleteUser(testUser2)
        userService.deleteUser(testUser3)
    }

    @Test
    fun createAndGetPostsOfUser() {
        val post1 = postService.createPost(CreatePostDTO("test1"), testUser1)
        val post2 = postService.createPost(CreatePostDTO("test2", post1.id), testUser1)
        val post3 = postService.createPost(CreatePostDTO("test3", post2.id), testUser1)

        val posts = postService.getPostsOfUser(testUser1.username)
        assert(posts.size == 3)
        assert(posts.map { post: Post -> post.text }.containsAll(listOf("test1", "test2", "test3")))
    }

    @Test
    fun pagingAndSortingOfTweets() {
        for (i in 0..15) {
            postService.createPost(CreatePostDTO("""test${i}"""), testUser1)
        }
        val posts = postService.getPostsOfUser(testUser1.username)
        assertEquals(posts.size, 10)

        val postsPage2 = postService.getPostsOfUser(testUser1.username, 1)
        assertEquals(postsPage2.size, 6)

        assert(posts[0].postDate > posts[1].postDate && posts[1].postDate > postsPage2[0].postDate)
    }

    @Test
    fun getFollowedUserTweets() {
        for (i in 0..4) {
            postService.createPost(CreatePostDTO("""test${i}"""), testUser1)
        }
        for (i in 0..3) {
            postService.createPost(CreatePostDTO("""test${i}"""), testUser2)
        }

        userService.followUser(testUser3.username, testUser1.username)
        userService.followUser(testUser3.username, testUser2.username)
        val posts = postService.getPostsOfFollowedUsers(testUser3.username)
        assertEquals(posts.size, 9)
    }
}