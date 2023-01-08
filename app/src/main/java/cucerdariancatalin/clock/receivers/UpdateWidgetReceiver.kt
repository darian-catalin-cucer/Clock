package cucerdariancatalin.clock.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cucerdariancatalin.clock.extensions.updateWidgets

class UpdateWidgetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.updateWidgets()
    }
}
