package ir.dastyar.assistant

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        if (intent.action != Intent.ACTION_BOOT_COMPLETED) {
            return
        }

        Notif.channel(context)

        val builder =
            android.app.Notification.Builder(
                context,
                Notif.CHANNEL_ID
            )
                .setContentTitle("دستیار")
                .setContentText("دستیار آماده است.")
                .setSmallIcon(android.R.drawable.ic_dialog_info)

        val manager =
            context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        manager.notify(10, builder.build())
    }
}