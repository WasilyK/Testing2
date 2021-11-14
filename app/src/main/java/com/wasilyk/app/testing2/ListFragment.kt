package com.wasilyk.app.testing2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wasilyk.app.testing2.databinding.FragmentListBinding

class ListFragment : Fragment(), ListAdapter.OnItemClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val data = DatasourceImpl().getData()
    private val listAdapter = ListAdapter(data, this)

    companion object {

        const val HEADER_TITLE = "header_title"

        fun newInstance(args: Bundle?): Fragment {
            val fragment = ListFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentListBinding.inflate(
        inflater, container, false
    ).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        setHeader()
    }

    private fun initRecyclerView() {
        binding.rv.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun setHeader() {
        arguments?.let { bundle ->
            val text = bundle.getString(HEADER_TITLE)
            text?.let { headerTitle ->
                binding.headerTitle.text = headerTitle
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onItemClick(pos: Int) {
        Toast.makeText(requireContext(), data[pos], Toast.LENGTH_SHORT)
            .show()
    }
}