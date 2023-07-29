package com.miguel4meiro.wonkasemployees.classes.modules.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.miguel4meiro.wonkasemployees.R
import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.ProfessionEnum
import com.miguel4meiro.wonkasemployees.databinding.BottomSheetLayoutBinding

class FilterBottomSheet(
    private val listener: FilterListenerInterface,
): BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetLayoutBinding

    var choosenGender: GenderEnum? = null
    var choosenProfession: ProfessionEnum? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.bottom_sheet_layout, container, false)
        binding = BottomSheetLayoutBinding.bind(view)

        setUpView()

        return binding.root
    }

    private fun setUpView() {
        setUpGenderRadioGroup()
        setupProfessionLayout()
        setupFilterBtn()
    }

    private fun setUpGenderRadioGroup() {
        when(choosenGender) {
            GenderEnum.MALE -> binding.genderRg.check(R.id.male_rbtn)
            GenderEnum.FEMALE -> binding.genderRg.check(R.id.female_rbtn)
            else -> { binding.genderRg.clearCheck() }
        }
        binding.genderRg.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.male_rbtn -> choosenGender = GenderEnum.MALE
                R.id.female_rbtn -> choosenGender = GenderEnum.FEMALE
            }
        }
    }

    private fun setupProfessionLayout() {
        val professionRg = binding.professionRg
        when(choosenProfession) {
            ProfessionEnum.DEVELOPER -> professionRg.check(R.id.developer_rbtn)
            ProfessionEnum.METALWORKER -> professionRg.check(R.id.metalworker_rbtn)
            ProfessionEnum.GEMCUTTER -> professionRg.check(R.id.gemcutter_rbtn)
            ProfessionEnum.MEDIC -> professionRg.check(R.id.medic_rbtn)
            ProfessionEnum.BREWER -> professionRg.check(R.id.brewer_rbtn)
            else -> { professionRg.clearCheck() }
        }
        professionRg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.developer_rbtn -> choosenProfession = ProfessionEnum.DEVELOPER
                R.id.metalworker_rbtn -> choosenProfession = ProfessionEnum.METALWORKER
                R.id.gemcutter_rbtn -> choosenProfession = ProfessionEnum.GEMCUTTER
                R.id.medic_rbtn -> choosenProfession = ProfessionEnum.MEDIC
                R.id.brewer_rbtn -> choosenProfession = ProfessionEnum.BREWER
            }
        }
    }

    private fun setupFilterBtn() {
        with(binding) {
            applyBtn.setOnClickListener {
                if (genderRg.checkedRadioButtonId == -1 && professionRg.checkedRadioButtonId == -1) return@setOnClickListener
                listener.applyFilter(choosenGender, choosenProfession)
            }
            resetBtn.setOnClickListener {
                genderRg.clearCheck()
                professionRg.clearCheck()
                choosenGender = null
                choosenProfession = null

                listener.resetFilter()
            }
        }
    }

    interface FilterListenerInterface {
        fun applyFilter(genderFilter: GenderEnum? = null, professionFilter: ProfessionEnum? = null)
        fun resetFilter()
    }
}