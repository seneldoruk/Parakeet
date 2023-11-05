package com.doruk.parakeet.Post

import com.doruk.parakeet.ParakeetUser.ParakeetUser
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDateTime

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @NotEmpty
    val text: String,

    @ManyToOne
    val user: ParakeetUser,

    @ManyToOne
    val parent: Post? = null,

    @OneToMany(mappedBy = "parent")
    val children: List<Post> = listOf(),

    @NotEmpty
    val postDate: LocalDateTime = LocalDateTime.now()
)