package cucerdariancatalin.clock.helpers

import androidx.room.TypeConverter
import cucerdariancatalin.clock.extensions.gson.gson
import cucerdariancatalin.clock.models.StateWrapper
import cucerdariancatalin.clock.models.TimerState

class Converters {

    @TypeConverter
    fun jsonToTimerState(value: String): TimerState {
        return try {
            gson.fromJson(value, StateWrapper::class.java).state
        } catch (e: Exception) {
            TimerState.Idle
        }
    }

    @TypeConverter
    fun timerStateToJson(state: TimerState) = gson.toJson(StateWrapper(state))
}
