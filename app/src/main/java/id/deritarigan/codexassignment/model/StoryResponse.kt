package id.deritarigan.codexassignment.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class StoryResponse : Parcelable {
    /**
     * by : tmlee
     * descendants : 1033
     * id : 22547283
     * kids : [22549090,22547672,22547921,22549035,22547579,22547754,22547715,22548871,22547361,22548441,22547760,22548069,22551479,22550164,22547581,22547817,22547365,22547464,22549247,22547644,22554790,22547637,22550287,22547983,22552444,22553092,22547445,22549278,22548543,22550082,22547734,22548703,22547713,22547502,22548560,22548280,22547799,22549378,22547511,22547557,22551566,22549324,22547422,22548635,22547424,22547708,22549303,22547551,22547400,22547552,22547432,22547591,22547483]
     * score : 1234
     * time : 1583945316
     * title : Covid-19 is now officially a pandemic, WHO says
     * type : story
     * url : https://www.npr.org/sections/goatsandsoda/2020/03/11/814474930/coronavirus-covid-19-is-now-officially-a-pandemic-who-says
     */
    var by: String? = null
    var descendants = 0
    var id = 0
    var score = 0
    var time = 0
    var text :String? = " ------ "
    var title: String? = ""
    var type: String? = ""
    var url: String? = ""
    var kids: List<Int?>? = listOf()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(by)
        dest.writeInt(descendants)
        dest.writeInt(id)
        dest.writeInt(score)
        dest.writeInt(time)
        dest.writeString(text)
        dest.writeString(title)
        dest.writeString(type)
        dest.writeString(url)
        dest.writeList(kids)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        by = `in`.readString()
        descendants = `in`.readInt()
        id = `in`.readInt()
        score = `in`.readInt()
        time = `in`.readInt()
        text = `in`.readString()
        title = `in`.readString()
        type = `in`.readString()
        url = `in`.readString()
        kids = ArrayList()
        `in`.readList(kids, Int::class.java.classLoader)
    }


    companion object CREATOR : Parcelable.Creator<StoryResponse> {
        override fun createFromParcel(parcel: Parcel): StoryResponse {
            return StoryResponse(parcel)
        }

        override fun newArray(size: Int): Array<StoryResponse?> {
            return arrayOfNulls(size)
        }
    }
}