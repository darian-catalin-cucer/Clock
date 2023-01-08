package cucerdariancatalin.clock.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cucerdariancatalin.clock.extensions.hideTimerNotification
import cucerdariancatalin.clock.helpers.INVALID_TIMER_ID
import cucerdariancatalin.clock.helpers.TIMER_ID
import cucerdariancatalin.clock.models.TimerEvent
import org.greenrobot.eventbus.EventBus

class HideTimerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val timerId = intent.getIntExtra(TIMER_ID, INVALID_TIMER_ID)
        context.hideTimerNotification(timerId)
        EventBus.getDefault().post(TimerEvent.Reset(timerId, ))
    }
}
