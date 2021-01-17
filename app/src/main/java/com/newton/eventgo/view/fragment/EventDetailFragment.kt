package com.newton.eventgo.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.newton.eventgo.R
import com.newton.eventgo.extensions.*
import com.newton.eventgo.models.EventDetail
import com.newton.eventgo.view.viewmodel.EventDetailViewModel
import kotlinx.android.synthetic.main.fragment_event_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventDetailFragment : Fragment() {

    private val args by navArgs<EventDetailFragmentArgs>()
    private val eventId by lazy { args.eventId }
    private val viewModel: EventDetailViewModel by viewModel { parametersOf(eventId) }
    private lateinit var event: EventDetail

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_detail, container, false)

        findEventId(eventId)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadEventId()
        event_detail_button_map.setOnClickListener {initGoogleMaps(event.lat, event.long)}
        setHasOptionsMenu(true)
    }

    private fun initGoogleMaps(lat: Double?, long: Double?) {
        val intentLocal = Intent(Intent.ACTION_VIEW)
        intentLocal.data = Uri.parse("geo:$lat,$long")
        startActivity(intentLocal)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.event_detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_share) {
            share()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initFields(event: EventDetail) {
        event.image.let {
            if (it != null) {
                event_detail_image.loadImage(it)
            }
        }
        this.event = event
        val descriptionWithSpaces = "${event.description}\n\n\n\n"
        event_detail_date.text = event.date?.fromTimesTamp()?.formatForBrazilianDate() ?: "-"
        event_detail_hour.text = event.date?.fromTimesTamp()?.formatForBrazilianHour() ?: "-"
        event_detail_title.text = event.title?.limit(36) ?: "-"
        event_detail_description.text = descriptionWithSpaces
        event_detail_price.text = event.price?.formatForBrazilianCoin() ?: "-"
    }

    private fun findEventId(id: Long) = viewModel.findEventById(id)

    private fun loadEventId() {
        viewModel.checkEventReturned().observe(viewLifecycleOwner, { eventDetailRequest ->
            if (eventDetailRequest != null) {
                val event = EventDetail()

                initFields(
                    event.eventDetailRequestToEventDetail(
                        eventDetailRequest
                    )
                )
            }
        })
    }

    private fun share() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun shareText() =
        "${event.title}\n\n" +
                "${event.description}\n" +
                "${event.date?.fromTimesTamp()?.formatForBrazilianDate() ?: "-"}\n" +
                (event.date?.fromTimesTamp()?.formatForBrazilianHour() ?: "-")
}