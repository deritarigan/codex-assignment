package id.deritarigan.codexassignment.ui.fragment


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.deritarigan.codexassignment.R
import id.deritarigan.codexassignment.model.StoryData
import id.deritarigan.codexassignment.model.StoryResponse
import id.deritarigan.codexassignment.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Story Detail"
        arguments?.getParcelable<StoryResponse>("data")?.apply {
            view.textViewTitle.text = title
            view.textViewPublisher.text = by
            view.textViewDescription.text = text
            view.imageViewFavorite.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(
                    "favorite",
                    title
                )
                requireActivity().onBackPressed()
                setFragmentResult(Activity.RESULT_OK, bundle)
            }
        }


    }

    fun setFragmentResult(resultCode: Int, data: Bundle) {
        (requireActivity() as MainActivity).onFragmentResult(resultCode, data)
    }


}
