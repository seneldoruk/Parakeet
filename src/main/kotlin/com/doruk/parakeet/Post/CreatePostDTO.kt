package com.doruk.parakeet.Post

data class CreatePostDTO(
    val text: String,
    val parentId: Long? = null
)