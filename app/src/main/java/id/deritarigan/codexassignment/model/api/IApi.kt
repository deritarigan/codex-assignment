package id.deritarigan.codexassignment.model.api

import id.deritarigan.codexassignment.model.StoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApi {

    @GET("topstories.json?print=pretty")
    fun getTopStories(): Call<MutableList<Int>>

    @GET("item/{story_id}.json?print=pretty")
    fun getStory(@Path("story_id") story_id: Int): Call<StoryResponse>

}