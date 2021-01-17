package com.newton.eventgo.view.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.newton.eventgo.R
import com.newton.eventgo.extensions.*
import com.newton.eventgo.models.Event

class EventAdapter(
    private val context: Context,
    private val events: MutableList<Event>,
    var onItemClickListener: (long: Long) -> Unit = {},
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var event: Event
        private val image: ImageView by lazy { itemView.findViewById(R.id.item_event_image) }
        private val date: TextView by lazy { itemView.findViewById(R.id.event_detail_date) }
        private val hour: TextView by lazy { itemView.findViewById(R.id.event_detail_hour) }
        private val price: TextView by lazy { itemView.findViewById(R.id.item_event_price) }
        private val title: TextView by lazy { itemView.findViewById(R.id.item_event_title) }

        fun bind(event: Event) {
            this.event = event
            event.image.let { imageAddress ->
                if (imageAddress != null) {
                    image.loadImage(imageAddress)
                }
            }
            date.text = event.date?.fromTimesTamp()?.formatForBrazilianDate() ?: "sem data informada"
            hour.text = event.date?.fromTimesTamp()?.formatForBrazilianHour() ?: "sem hora informada"
            price.text = event.price?.formatForBrazilianCoin()
            title.text = event.title
        }

        init {
            itemView.setOnClickListener {
                if (::event.isInitialized) {
                    event.id?.let { it1 -> onItemClickListener(it1) }
                }
            }
        }
    }

}
