package com.madonasyombua.dictionaryurban.data.response

import com.google.gson.annotations.SerializedName

/**
 *
 * @ SerializedName annotation indicates the annotated member should be serialized to JSON
 * with the provided name value as its field name.
 *
 * Brief Sample of what their JSON data looks like The list has 11 items:
 * Included below:
 * "list":[10 items
 * 0:{11 items
 * "definition":"The only proper response to something that makes absolutely [no sense]."
 * "permalink":"http://wat.urbanup.com/3322419"
 * "thumbs_up":3662
 * "sound_urls":[...]3 items
 * "author":"watwat"
 * "word":"wat"
 * "defid":3322419
 * "current_vote":""
 * "written_on":"2008-09-04T00:00:00.000Z"
 * "example":"1: If all the animals on the equator were capable of flattery, Halloween and Easter would fall on the same day. 2: wat 1: Wow your cock is almost as big as my dad's. 2: wat 1: I accidentially a whole [coke bottle] 2: You accidentially what? 1: A whole coke bottle 2: wat"
 * "thumbs_down":423
}
 */
data class BaseResponse(@SerializedName ("list") val wordList: List<Word>){
    //check when empty
    fun whenEmpty() = wordList.isEmpty()
}
//json data
data class Word(
    @SerializedName("definition") val definition: String,
    @SerializedName("thumbs_up") val thumbsUpNumber: Int,
    @SerializedName("defid") val defId: Int,
    @SerializedName("author") val authorName: String,
    @SerializedName("word") val word: String,
    @SerializedName("written_on") val writtenOn: String,
    @SerializedName("example") val example: String,
    @SerializedName("thumbs_down") val thumbsDownNumber: Int
)
