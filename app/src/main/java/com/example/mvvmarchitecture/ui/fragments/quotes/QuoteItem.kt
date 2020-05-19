package com.example.mvvmarchitecture.ui.fragments.quotes

import android.view.View
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.data.database.entities.Quote
import com.example.mvvmarchitecture.databinding.QuoteItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<QuoteItemBinding>() {
    override fun getLayout() = R.layout.quote_item

    override fun bind(viewBinding: QuoteItemBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

    override fun initializeViewBinding(view: View): QuoteItemBinding {
        return QuoteItemBinding.bind(view)
    }
}