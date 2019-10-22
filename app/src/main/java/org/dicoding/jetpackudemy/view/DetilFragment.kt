package org.dicoding.jetpackudemy.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detil.*


import org.dicoding.jetpackudemy.R
import org.dicoding.jetpackudemy.viewmodel.detilViewModel

class DetilFragment : Fragment() {

    private lateinit var viewModel : detilViewModel
    private var catUid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(detilViewModel::class.java)
        viewModel.fetch()

        arguments?.let {
            catUid = DetilFragmentArgs.fromBundle(it).catUid
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogs.observe(this, Observer {dogs ->
            dogs?.let {
                dogName.text = dogs.dogBreed
                dogLifespan.text = dogs.lifeSpan
                dogPurpose.text = dogs.breedFor
                dogTemprement.text = dogs.temperament
            }
        })
    }


}
