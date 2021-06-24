package com.example.visual.data

data class Information2(var title: Array<String>,var text: Array<String?>,var image: Array<Int>,var visibility: Array<Int>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Information2

        if (!title.contentEquals(other.title)) return false
        if (!text.contentEquals(other.text)) return false
        if (!image.contentEquals(other.image)) return false
        if (!visibility.contentEquals(other.visibility)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.contentHashCode()
        result = 31 * result + text.contentHashCode()
        result = 31 * result + image.contentHashCode()
        result = 31 * result + visibility.contentHashCode()
        return result
    }
}