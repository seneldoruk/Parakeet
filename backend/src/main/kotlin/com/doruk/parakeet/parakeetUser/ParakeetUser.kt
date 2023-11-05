package com.doruk.parakeet.parakeetUser

import com.doruk.parakeet.post.Post
import jakarta.persistence.*


@Entity
data class ParakeetUser(
    @Id val username: String,
    val password: String,

    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = [JoinColumn(name = "followed_id")],
        inverseJoinColumns = [JoinColumn(name = "follower_id")]
    )
    var followers: MutableSet<ParakeetUser> = mutableSetOf(),

    @ManyToMany(mappedBy = "followers")
    var following: MutableSet<ParakeetUser> = mutableSetOf(),

    @OneToMany(mappedBy = "user")
    var posts: List<Post> = listOf()
) {

    override fun equals(other: Any?): Boolean {
        if (other !is ParakeetUser) return false
        return this.username == other.username
    }

    override fun hashCode(): Int {
        return username.hashCode()
    }

    override fun toString(): String {
        return username
    }
}