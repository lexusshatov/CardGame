package com.mauz.narutogame.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mauz.narutogame.R
import com.mauz.narutogame.core.usecase.RemoveItemUseCase
import com.mauz.narutogame.databinding.DialogRemoveItemBinding
import com.mauz.narutogame.util.setOnProgressChangeListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RemoveItemDialog : DialogFragment(R.layout.dialog_remove_item) {

    override fun getTheme() = R.style.RoundedCornersDialog

    private val binding by viewBinding<DialogRemoveItemBinding>()
    private val args by navArgs<RemoveItemDialogArgs>()

    @Inject
    lateinit var removeItemUseCase: RemoveItemUseCase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            if (args.item.count == 1) {
                count.visibility = View.GONE
                seekbar.visibility = View.GONE
            }
            seekbar.max = args.item.count
            count.text = seekbar.min.toString()
            seekbar.setOnProgressChangeListener { progress ->
                count.text = progress.toString()
            }

            yes.setOnClickListener {
                val params = RemoveItemUseCase.Params(
                    item = args.item,
                    count = seekbar.progress)
                removeItemUseCase.invoke(params, lifecycleScope)
                dismiss()
            }
            no.setOnClickListener { dismiss() }
        }
    }
}