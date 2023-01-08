package cucerdariancatalin.clock.services

import android.app.IntentService
import android.content.Intent
import cucerdariancatalin.clock.extensions.config
import cucerdariancatalin.clock.extensions.dbHelper
import cucerdariancatalin.clock.extensions.hideNotification
import cucerdariancatalin.clock.extensions.setupAlarmClock
import cucerdariancatalin.clock.helpers.ALARM_ID
import com.simplemobiletools.commons.helpers.MINUTE_SECONDS

class SnoozeService : IntentService("Snooze") {
    override fun onHandleIntent(intent: Intent?) {
        val id = intent!!.getIntExtra(ALARM_ID, -1)
        val alarm = dbHelper.getAlarmWithId(id) ?: return
        hideNotification(id)
        setupAlarmClock(alarm, config.snoozeTime * MINUTE_SECONDS)
    }
}
