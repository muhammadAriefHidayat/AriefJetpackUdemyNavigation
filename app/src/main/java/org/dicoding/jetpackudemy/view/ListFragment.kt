package org.dicoding.jetpackudemy.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*

import org.dicoding.jetpackudemy.R
import org.dicoding.jetpackudemy.viewmodel.listViewModel

class ListFragment : Fragment() {

    private lateinit var viewModel : listViewModel
    private var adapter = DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(listViewModel::class.java)
        viewModel.refresh()

        dogRecyclerView?.let {
            dogRecyclerView.adapter = adapter
            dogRecyclerView.layoutManager = LinearLayoutManager(context)
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.dogs.observe(this, Observer { dogs ->
            dogs?.let {
                dogRecyclerView.visibility = View.VISIBLE
                adapter.UpdateDogList(dogs)
            }
        })

        viewModel.dogsLoadEror.observe(this, Observer { isError ->
            isError?.let {
                errorList.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            loading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    errorList.visibility = View.GONE
                    dogRecyclerView.visibility = View.GONE
                }
            }
        })
    }
}
