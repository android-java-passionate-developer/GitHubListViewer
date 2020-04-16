package com.example.pujarani.githubviewerapplication.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Puja.Rani on 14-04-2020.
 */
@Entity(tableName = "Git")
class GitHubDatModel: Serializable{


    @SerializedName("login")
    @Expose
    var login: String? = null

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("node_id")
    @Expose
    var node_id: String? = null
    @SerializedName("avatar_url")
    @Expose
    var avatar_url: String? = null
    @SerializedName("gravatar_id")
    @Expose
    var gravatar_id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("html_url")
    @Expose
    var html_url: String? = null
    @SerializedName("followers_url")
    @Expose
    var followers_url: String? = null
    @SerializedName("following_url")
    @Expose
    var following_url: String? = null
    @SerializedName("gists_url")
    @Expose
    var gists_url: String? = null
    @SerializedName("starred_url")
    @Expose
    var starred_url: String? = null
    @SerializedName("subscriptions_url")
    @Expose
    var subscriptions_url: String? = null
    @SerializedName("organizations_url")
    @Expose
    var organizations_url: String? = null
    @SerializedName("repos_url")
    @Expose
    var repos_url: String? = null
    @SerializedName("events_url")
    @Expose
    var events_url: String? = null
    @SerializedName("received_events_url")
    @Expose
    var received_events_url: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("site_admin")
    @Expose
    var site_admin: String? = null
    @SerializedName("score")
    @Expose
    var score: String? = null
}