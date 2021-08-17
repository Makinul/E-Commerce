package com.makinul.ecommerce

import android.app.Dialog
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun showSimpleDialog(@StringRes resId: Int) {
        showSimpleDialog(getString(resId))
    }

    fun showSimpleDialog(message: String) {
        if (activity == null) {
            Toast.makeText(
                context, message,
                Toast.LENGTH_LONG
            ).show()
            return
        }
        val alertDialog: Dialog = AlertDialog.Builder(requireActivity())
            .setMessage(message)
            .setPositiveButton(
                R.string.ok
            ) { _: DialogInterface?, _: Int -> }
            .create()
        alertDialog.show()
    }

    @JvmOverloads
    fun showLog(message: String? = "Test Log") {
        if (message == null || message.isEmpty()) return
        Log.v(TAG, message)
    }

    @JvmOverloads
    fun showToast(@StringRes resourceId: Int = R.string.work_in_progress) {
        showToast(getString(resourceId))
    }

    fun showToast(message: String?) {
        if (message == null || message.isEmpty()) return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "BaseFragment"
    }
}