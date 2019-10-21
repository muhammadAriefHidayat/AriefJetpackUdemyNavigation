package org.dicoding.jetpackudemy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dog.view.*
import org.dicoding.jetpackudemy.R
import org.dicoding.jetpackudemy.model.DogBreed

class DogsListAdapter(val dogList: ArrayList<DogBreed>):RecyclerView.Adapter<DogsListAdapter.dogViewHoler>() {

    fun UpdateDogList(newdogList: List<DogBreed>){
        dogList.clear()
        dogList.addAll(newdogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dogViewHoler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog,parent,false)
        return dogViewHoler(view)
    }

    override fun getItemCount()= dogList.size


    override fun onBindViewHolder(holder: dogViewHoler, position: Int) {
        holder.view.name.text = dogList[position].lifeSpan
        holder.view.lifeSpan.text = dogList[position].lifeSpan
    }

    class dogViewHoler(var view: View):RecyclerView.ViewHolder(view)
}