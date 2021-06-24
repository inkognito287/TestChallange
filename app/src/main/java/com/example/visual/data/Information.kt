package com.example.visual.data



data class Information(var imgId: Array<Int>, var title: Array<String>){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Information

        if (!imgId.contentEquals(other.imgId)) return false
        if (!title.contentEquals(other.title)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imgId.contentHashCode()
        result = 31 * result + title.contentHashCode()
        return result
    }

}