package cucerdariancatalin.clock.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cucerdariancatalin.clock.extensions.dbHelper
import cucerdariancatalin.clock.extensions.hideNotification
import cucerdariancatalin.clock.extensions.updateWidgets
import cucerdariancatalin.clock.helpers.ALARM_ID
import com.simplemobiletools.commons.helpers.ensureBackgroundThread

class HideAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getIntExtra(ALARM_ID, -1)
        context.hideNotification(id)

        ensureBackgroundThread {
            val alarm = context.dbHelper.getAlarmWithId(id)
            if (alarm != null && alarm.days < 0) {
                context.dbHelper.updateAlarmEnabledState(alarm.id, false)
                context.updateWidgets()
            }
        }
    }
}
