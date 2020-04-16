package com.example.pujarani.githubviewerapplication.View

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pujarani.githubviewerapplication.Model.GitHubDatModel
import com.example.pujarani.githubviewerapplication.R

/**
 * Created by Puja.Rani on 04-12-2019.
 */
class GithubtrendinglistAdapter(private var lists: List<GitHubDatModel>, private var context: Context): RecyclerView.Adapter<GithubtrendinglistAdapter.GitHubListViewHolder>(){

    var curContext: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubListViewHolder {
        var layoutInflater: View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.github_list_item, parent, false)

        itemViewHolder = GitHubListViewHolder(layoutInflater)
        return itemViewHolder;
    }

    override fun onBindViewHolder(holder: GitHubListViewHolder, position: Int) {
        val gitHub = lists[position]
        itemViewHolder.textViewName.text = gitHub.login
        Glide.with(itemViewHolder.imageView.context).load(gitHub.avatar_url).into(itemViewHolder.imageView)
        itemViewHolder.itemView.setOnClickListener(View.OnClickListener {
            if (curContext is onClickViewItem) {
                (curContext as onClickViewItem).onClickCardView(gitHub)
            }
        })

    }

    private lateinit var itemViewHolder: GitHubListViewHolder



    override fun getItemCount(): Int {
        return lists.size;
    }


    fun update(data: List<GitHubDatModel>){
        lists = data
        notifyDataSetChanged()
    }

    class GitHubListViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val textViewName: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_profile_img)
        val cardView: CardView = view.findViewById(R.id.card_view)
    }

    interface onClickViewItem{
        fun onClickCardView(data: GitHubDatModel);
    }
}