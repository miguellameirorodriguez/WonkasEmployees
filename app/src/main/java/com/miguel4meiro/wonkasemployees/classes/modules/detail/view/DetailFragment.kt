package com.miguel4meiro.wonkasemployees.classes.modules.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miguel4meiro.wonkasemployees.R
import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.Loompa
import com.miguel4meiro.wonkasemployees.classes.models.app.getValue
import com.miguel4meiro.wonkasemployees.classes.modules.detail.viewmodel.DetailViewModelInterface
import com.miguel4meiro.wonkasemployees.databinding.DetailFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

const val LOOMPA_ID = "loompaID"

class DetailFragment: Fragment() {
    private val viewModel: DetailViewModelInterface by viewModel()
    private lateinit var binding: DetailFragmentBinding

    companion object {
        fun newInstance(loompaId: Int): DetailFragment {
            val fragment = DetailFragment()

            val args = Bundle()
            args.putInt(LOOMPA_ID, loompaId)

            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(layoutInflater)

        setupBtn()

        val loompaId = arguments?.getInt(LOOMPA_ID) ?: 1
        viewModel.getLoompa(loompaId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewModelScope.launch {
            viewModel.uiState.collect {  uiState->
                loadLoompaData(uiState.loompa)
                showProgressBar(uiState.isLoading)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        setBackBtn()
    }

    private fun showProgressBar(isLoading: Boolean) {
        with(binding) {
            detailPb.isVisible = isLoading
            detailLayout.isVisible = !isLoading
        }
    }

    private fun loadLoompaData(loompa: Loompa?) {
        if (loompa == null) return

        Glide.with(this)
            .load(loompa.image)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(binding.profileIv)

        binding.nameTv.text = loompa.fullName
        binding.genderIv.setImageResource(if (loompa.gender == GenderEnum.MALE) R.drawable.ic_male else R.drawable.ic_female)
        binding.emailTv.text = loompa.email
        binding.professionTv.text = loompa.profession.getValue()
        binding.countryTv.text = loompa.country

        binding.ageBox.titleTv.text = getString(R.string.detail_age)
        binding.ageBox.valueTv.text = loompa.age.toString()

        binding.heightBox.titleTv.text = getString(R.string.detail_height)
        binding.heightBox.valueTv.text = getString(R.string.detail_cm, loompa.height)

        binding.foodBox.titleTv.text = getString(R.string.detail_food)
        binding.foodBox.valueTv.text = loompa.favorites.food

        binding.colorBox.titleTv.text = getString(R.string.detail_color)
        binding.colorBox.valueTv.text = loompa.favorites.color

        binding.randomStringTv.text = loompa.favorites.randomString
        binding.songTv.text = loompa.favorites.song
    }

    private fun setupBtn() {
        with(binding) {
            backBtn.setOnClickListener { setBackBtn() }
            randomStringBtn.setOnClickListener {
                if (randomStringScroll.visibility == View.GONE) {
                    randomStringScroll.visibility = View.VISIBLE
                } else {
                    randomStringScroll.visibility = View.GONE
                }
            }
            songBtn.setOnClickListener {
                if (songScroll.visibility == View.GONE) {
                    songScroll.visibility = View.VISIBLE
                } else {
                    songScroll.visibility = View.GONE
                }
            }
        }
    }

    private fun setBackBtn() {
        parentFragment?.view?.findViewById<Button>(R.id.filter_btn)?.visibility = View.VISIBLE
        activity?.supportFragmentManager?.popBackStack()
    }

}