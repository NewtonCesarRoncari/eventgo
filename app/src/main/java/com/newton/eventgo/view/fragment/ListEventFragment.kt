package com.newton.eventgo.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.newton.eventgo.R
import com.newton.eventgo.models.Event
import com.newton.eventgo.view.recyclerview.adapter.EventAdapter
import com.newton.eventgo.view.viewmodel.ListEventViewModel
import com.newton.eventgo.view.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_list_event.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListEventFragment : Fragment() {

    private lateinit var adapter: EventAdapter
    private val viewModel: ListEventViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_event, container, false)
        (activity as AppCompatActivity).supportActionBar!!.show()

        checkStateLogin()
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getEvents()
        initEventAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_event_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_user_logout) {
            loginViewModel.logout()
            goToLoginFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initEventAdapter() {
        viewModel.checkEventsReturned()
            .observe(viewLifecycleOwner, { eventsRequest ->
                if (eventsRequest != null) {

                    val event = Event()
                    val events = event.eventsRequestToEvents(eventsRequest)

                    adapter = EventAdapter(requireContext(), events)
                    event_list_rv.adapter = adapter
                    adapter.onItemClickListener = { eventId ->
                        goToEventDetailFragment(eventId)
                    }
                }
            })
    }

    private fun goToEventDetailFragment(eventId: Long) {
        val direction = ListEventFragmentDirections
            .actionHomeFragmentToEventDetailFragment(eventId)
        navController.navigate(direction)
    }

    private fun checkStateLogin() {
        if (!loginViewModel.isLogged()) {
            goToLoginFragment()
        }
    }

    private fun goToLoginFragment() {
        val direction = ListEventFragmentDirections
            .actionHomeFragmentToLoginFragment()
        navController.navigate(direction)
    }

}