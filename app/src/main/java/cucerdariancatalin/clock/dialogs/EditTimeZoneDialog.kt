package cucerdariancatalin.clock.dialogs

import cucerdariancatalin.clock.R
import cucerdariancatalin.clock.activities.SimpleActivity
import cucerdariancatalin.clock.extensions.config
import cucerdariancatalin.clock.extensions.getEditedTimeZonesMap
import cucerdariancatalin.clock.extensions.getModifiedTimeZoneTitle
import cucerdariancatalin.clock.helpers.EDITED_TIME_ZONE_SEPARATOR
import cucerdariancatalin.clock.helpers.getDefaultTimeZoneTitle
import cucerdariancatalin.clock.models.MyTimeZone
import com.simplemobiletools.commons.extensions.getAlertDialogBuilder
import com.simplemobiletools.commons.extensions.setupDialogStuff
import com.simplemobiletools.commons.extensions.showKeyboard
import com.simplemobiletools.commons.extensions.value
import kotlinx.android.synthetic.main.dialog_edit_time_zone.view.*

class EditTimeZoneDialog(val activity: SimpleActivity, val myTimeZone: MyTimeZone, val callback: () -> Unit) {

    init {
        val view = activity.layoutInflater.inflate(R.layout.dialog_edit_time_zone, null).apply {
            edit_time_zone_title.setText(activity.getModifiedTimeZoneTitle(myTimeZone.id))
            edit_time_zone_label.setText(getDefaultTimeZoneTitle(myTimeZone.id))
        }

        activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.ok) { dialog, which -> dialogConfirmed(view.edit_time_zone_title.value) }
            .setNegativeButton(R.string.cancel, null)
            .apply {
                activity.setupDialogStuff(view, this) { alertDialog ->
                    alertDialog.showKeyboard(view.edit_time_zone_title)
                }
            }
    }

    private fun dialogConfirmed(newTitle: String) {
        val editedTitlesMap = activity.getEditedTimeZonesMap()

        if (newTitle.isEmpty()) {
            editedTitlesMap.remove(myTimeZone.id)
        } else {
            editedTitlesMap[myTimeZone.id] = newTitle
        }

        val newTitlesSet = HashSet<String>()
        for ((key, value) in editedTitlesMap) {
            newTitlesSet.add("$key$EDITED_TIME_ZONE_SEPARATOR$value")
        }

        activity.config.editedTimeZoneTitles = newTitlesSet
        callback()
    }
}
