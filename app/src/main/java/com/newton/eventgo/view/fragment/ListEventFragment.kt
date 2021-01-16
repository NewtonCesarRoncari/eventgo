package com.newton.eventgo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newton.eventgo.R
import com.newton.eventgo.models.Event
import com.newton.eventgo.view.recyclerview.adapter.EventAdapter
import kotlinx.android.synthetic.main.fragment_list_event.*
import java.math.BigDecimal
import java.util.*

class ListEventFragment : Fragment() {

    private lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initEventAdapter(
            mutableListOf(
                Event(
                    5,
                    "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
                    "Feira de adoção de animais na Redenção",
                    Date(),
                    BigDecimal.ONE
                ),
                Event(
                    5,
                    "https://www.infoescola.com/wp-content/uploads/2011/08/Pal%C3%A1cio-de-Buckingham_668643088.jpg",
                    "Doação de roupas",
                    Date(),
                    BigDecimal.ONE
                ),
                Event(
                    5,
                    "http://www.fernaogaivota.com.br/documents/10179/1665610/feira-troca-de-livros.jpg",
                    "Feira de Troca de Livros",
                    Date(),
                    BigDecimal.ONE
                ),
                Event(
                    5,
                    "https://i2.wp.com/assentopublico.com.br/wp-content/uploads/2017/07/Feira-de-alimentos-org%C3%A2nicos-naturais-e-artesanais-ganha-um-novo-espa%C3%A7o-em-Ribeir%C3%A3o.jpg",
                    "Feira de Produtos Naturais e Orgânicos",
                    Date(),
                    BigDecimal.ONE
                )
            )
        )
    }

    private fun initEventAdapter(events: MutableList<Event>) {
        adapter = EventAdapter(requireContext(), events)
        event_list_rv.adapter = adapter
    }

}