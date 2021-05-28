package com.alirnp.babalhavaej.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alirnp.babalhavaej.R
import com.alirnp.babalhavaej.common.PriceUtil
import com.alirnp.babalhavaej.databinding.ItemHomeItemsBinding
import com.alirnp.babalhavaej.databinding.ItemReportCardBinding
import com.alirnp.babalhavaej.model.HomeItem
import com.alirnp.babalhavaej.model.ReportCard

private const val itemTypeHome = 21
private const val itemTypeReportCard = 22

class HomeItemsAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mRecyclerView : RecyclerView

    private var items: List<HomeItem>? = null
    private var reportCard: ReportCard? = null

    var listener : OnItemClickListener? = null

    fun setItems(items: List<HomeItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setReportCard(reportCard: ReportCard) {
        this.reportCard = reportCard
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun getItemViewType(position: Int): Int {

        reportCard?.let {
            if (position == itemCount - 1)
                return itemTypeReportCard
        }

        return itemTypeHome
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            itemTypeReportCard -> {
                val binding = DataBindingUtil.inflate<ItemReportCardBinding>(
                    inflater,
                    R.layout.item_report_card,
                    parent,
                    false
                )
                return ReportCardHolder(binding)

            }
            else -> {
                val binding = DataBindingUtil.inflate<ItemHomeItemsBinding>(
                    inflater,
                    R.layout.item_home_items,
                    parent,
                    false
                )
                return HomeItemHolder(binding)
            }


        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HomeItemHolder -> items?.let { holder.bind(it, position) }
            is ReportCardHolder -> reportCard?.let { holder.bind(it) }
        }

    }

    override fun getItemCount(): Int {
        val itemsCount = items?.size ?: 0

        reportCard?.let {
            return itemsCount + 1

        } ?: kotlin.run {
            return itemsCount
        }
    }

    class HomeItemHolder(private val binding: ItemHomeItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val blue =
            ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.blue))
        private val red =
            ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.red))
        private val green =
            ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.green))
        private val orange =
            ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.orange))
        private val purple =
            ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.purple))
        private val pink =
            ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.pink))


        fun bind(items: List<HomeItem>, position: Int) {
            val item = items[position]

            binding.textViewName.text = item.name
            ImageViewCompat.setImageTintList(
                binding.imageViewIcon,
                getIconColorByPosition(position)
            )
        }

        private fun getIconColorByPosition(position: Int): ColorStateList? {
            return when (position) {
                in 0..2 -> blue
                in 3..5 -> red
                in 6..8 -> green
                in 9..11 -> orange
                in 12..14 -> purple
                in 15..17 -> pink
                else -> blue
            }
        }
    }

    class ReportCardHolder(private val binding: ItemReportCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(reportCard: ReportCard) {
            binding.textViewCharities.text = PriceUtil.splitDigits(reportCard.charities)
            binding.textViewNeedsMet.text = PriceUtil.splitDigits(reportCard.needsMet)
            binding.textViewSupporters.text = PriceUtil.splitDigits(reportCard.supporters)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(item : HomeItem)
    }
}