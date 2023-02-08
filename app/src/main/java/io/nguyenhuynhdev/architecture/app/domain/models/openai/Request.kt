package io.nguyenhuynhdev.architecture.app.domain.models.openai

data class Request(val model: String, val prompt: String, val max_tokens: Int, val temperature: Float)
