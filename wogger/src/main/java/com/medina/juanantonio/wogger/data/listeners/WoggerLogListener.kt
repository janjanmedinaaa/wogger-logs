package com.medina.juanantonio.wogger.data.listeners

import androidx.databinding.ObservableList
import com.medina.juanantonio.wogger.data.models.WoggerLog

open class WoggerLogListener : ObservableList.OnListChangedCallback<ObservableList<WoggerLog>>() {
    override fun onChanged(sender: ObservableList<WoggerLog>?) {
    }

    override fun onItemRangeRemoved(
        sender: ObservableList<WoggerLog>?,
        positionStart: Int,
        itemCount: Int
    ) {
    }

    override fun onItemRangeMoved(
        sender: ObservableList<WoggerLog>?,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int
    ) {
    }

    override fun onItemRangeInserted(
        sender: ObservableList<WoggerLog>?,
        positionStart: Int,
        itemCount: Int
    ) {
    }

    override fun onItemRangeChanged(
        sender: ObservableList<WoggerLog>?,
        positionStart: Int,
        itemCount: Int
    ) {
    }

}