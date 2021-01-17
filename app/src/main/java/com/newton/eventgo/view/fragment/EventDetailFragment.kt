package com.newton.eventgo.view.fragment

import android.animation.Animator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.newton.eventgo.R
import com.newton.eventgo.extensions.*
import com.newton.eventgo.models.EventDetail
import com.newton.eventgo.models.dto.CheckinRequest
import com.newton.eventgo.view.viewmodel.EventDetailViewModel
import com.newton.eventgo.view.viewmodel.UserDataViewModel
import kotlinx.android.synthetic.main.fragment_event_detail.*
import kotlinx.android.synthetic.main.fragment_list_event.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventDetailFragment : Fragment() {

    private val args by navArgs<EventDetailFragmentArgs>()
    private val eventId by lazy { args.eventId }
    private val viewModel: EventDetailViewModel by viewModel { parametersOf(eventId) }
    private val userDataViewModel: UserDataViewModel by viewModel()
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
        initLoading()

        loadEventId()
        event_detail_btn_checkin.setOnClickListener {
            disableButtonCheckin()
            sendCheckin()
        }
        event_detail_button_map.setOnClickListener { initGoogleMaps(event.lat, event.long) }
        setHasOptionsMenu(true)
    }

    private fun disableButtonCheckin() {
        event_detail_btn_checkin.isClickable = false
        event_detail_btn_checkin.isFocusable = false
        event_detail_btn_checkin.isEnabled = false
    }

    private fun sendCheckin() {
        if (userDataViewModel.getName != null && userDataViewModel.getEmail != null) {
            viewModel.postCheckin(CheckinRequest(
                eventId,
                userDataViewModel.getName,
                userDataViewModel.getEmail
            ))
        }
        initAnimation()
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
                finishLoading()
            }
        })
    }

    private fun initAnimation() {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        event_detail_button_map.visibility = GONE
        event_detail_btn_checkin.visibility = GONE
        constraint_confirmed_checkin.visibility = VISIBLE
        confirmed_animation.setAnimation("anim/confirmed.json")
        confirmed_animation.playAnimation()
        confirmed_animation.addAnimatorListener(animatorListener())
    }

    private fun animatorListener(): Animator.AnimatorListener {
        val animationTag = "Animation:"
        return object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                Log.e(animationTag, "cancel")
            }

            override fun onAnimationEnd(animation: Animator) {
                try {
                    (activity as AppCompatActivity).supportActionBar!!.show()
                    event_detail_button_map.visibility = VISIBLE
                    event_detail_btn_checkin.visibility = VISIBLE
                    constraint_confirmed_checkin.visibility = GONE
                    confirmed_animation.visibility = GONE
                } catch (ex: Exception) {
                    ex.toString()
                }
            }

            override fun onAnimationCancel(animation: Animator) {
                Log.e(animationTag, "cancel")
            }

            override fun onAnimationRepeat(animation: Animator) {
                Log.e(animationTag, "repeat")
            }
        }
    }

    private fun initLoading() {
        if (event_detail_progressbar != null)
            event_detail_progressbar.visibility = VISIBLE
    }

    private fun finishLoading() {
        if (event_detail_progressbar != null)
            event_detail_progressbar.visibility = GONE
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