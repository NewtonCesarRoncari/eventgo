package com.newton.eventgo.extensions

import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog

fun Fragment.showMessageErrorConnection() {
    MaterialDialog.Builder(requireContext())
        .title("Erro")
        .content("Aparentemente ocorreu um problema de conexão, porfavor tente novamente mais tarde")
        .positiveText("OK")
        .cancelable(false)
        .canceledOnTouchOutside(false)
        .show()
}