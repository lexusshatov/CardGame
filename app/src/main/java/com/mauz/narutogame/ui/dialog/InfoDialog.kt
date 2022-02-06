package com.mauz.narutogame.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mauz.narutogame.R
import com.mauz.narutogame.databinding.DialogInfoBinding

class InfoDialog : DialogFragment(R.layout.dialog_info) {

    override fun getTheme() = R.style.RoundedCornersDialog

    private val binding by viewBinding<DialogInfoBinding>()
    private val args by navArgs<InfoDialogArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            info.text = args.info
            ok.setOnClickListener { dismiss() }
        }
    }
}