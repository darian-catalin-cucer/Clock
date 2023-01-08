package cucerdariancatalin.clock.dialogs

import cucerdariancatalin.clock.R
import cucerdariancatalin.clock.activities.SimpleActivity
import cucerdariancatalin.clock.adapters.SelectTimeZonesAdapter
import cucerdariancatalin.clock.extensions.config
import cucerdariancatalin.clock.helpers.getAllTimeZones
import com.simplemobiletools.commons.extensions.getAlertDialogBuilder
import com.simplemobiletools.commons.extensions.setupDialogStuff
import kotlinx.android.synthetic.main.dialog_select_time_zones.view.*

class AddTimeZonesDialog(val activity: SimpleActivity, private val callback: () -> Unit) {
    private var view = activity.layoutInflater.inflate(R.layout.dialog_select_time_zones, null)

    init {
        view.select_time_zones_list.adapter = SelectTimeZonesAdapter(activity, getAllTimeZones())

        activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.ok) { dialog, which -> dialogConfirmed() }
            .setNegativeButton(R.string.cancel, null)
            .apply {
                activity.setupDialogStuff(view, this)
            }
    }

    private fun dialogConfirmed() {
        val adapter = view?.select_time_zones_list?.adapter as? SelectTimeZonesAdapter
        val selectedTimeZones = adapter?.selectedKeys?.map { it.toString() }?.toHashSet() ?: LinkedHashSet()
        activity.config.selectedTimeZones = selectedTimeZones
        callback()
    }
}
