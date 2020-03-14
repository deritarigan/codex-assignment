package id.deritarigan.codexassignment.ui.fragment

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import id.deritarigan.codexassignment.R
import id.deritarigan.codexassignment.model.StoryResponse
import id.deritarigan.codexassignment.ui.activity.MainActivity
import id.deritarigan.codexassignment.ui.adapter.StoryAdapter
import id.deritarigan.codexassignment.ui.viewmodel.StoriesViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment(), StoryAdapter.StoryCallback {
    private val viewModel by viewModel<StoriesViewModel>()
    private val storyAdapter by lazy {
        StoryAdapter(this)
    }
    lateinit var nav: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity() as MainActivity).bundlePublisher.observe(this@ListFragment, Observer {
            onFragmentResult(RESULT_OK, it)
        })

        lifecycle.addObserver(viewModel)
        viewModel.apply {
            isLoading.observe(this@ListFragment, Observer { isLoading ->
                if (isLoading) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            })

            topStoriesId.observe(this@ListFragment, Observer { response ->
                progressBar.max = response.size
                isLoading.postValue(true)
                response?.forEach { id ->
                    onGetDataStories( id)
                }
            })

            totalData.observe(this@ListFragment, Observer { totalData->
                progressBar.progress = totalData
                Log.d("ProgressLog","$totalData / ${topStoriesId.value?.size}")
                if (totalData.equals(topStoriesId.value?.size)){
                    isLoading.postValue(false)
                }
            })

            storyData.observe(this@ListFragment, Observer { data ->
                storyAdapter.apply {
                    listData.add(data)
                    notifyDataSetChanged()
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Top Stories"
        nav = Navigation.findNavController(view)

        view.recyclerView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            setHasFixedSize(false)
            adapter = storyAdapter
        }


    }


    override fun onClickStory(data: StoryResponse?) {
        val bundle = Bundle()
        bundle.putParcelable("data", data)
        nav.navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }


    fun onFragmentResult(resultCode: Int, data: Bundle) {
        if (resultCode == RESULT_OK)
        textViewFavorite.text = data.getString("favorite")
    }

}