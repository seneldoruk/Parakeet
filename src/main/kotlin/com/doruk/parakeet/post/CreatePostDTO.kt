package com.doruk.parakeet.post

data class CreatePostDTO(
    val text: String,
    val parentId: Long? = null
)