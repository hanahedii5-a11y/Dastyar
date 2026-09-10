package ir.dastyar.assistant

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Notif.channel(context)

        val text =
            intent.getStringExtra("text") ?: "یادآوری"

        val openIntent = Intent(context, MainActivity::class.java)

        val pending = PendingIntent.getActivity(
            context,
            100,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )

        val builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                android.app.Notification.Builder(
                    context,
                    Notif.CHANNEL_ID
                )
            } else {
                android.app.Notification.Builder(context)
            }

        builder
            .setContentTitle("دستیار")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setContentIntent(pending)

        val manager =
            context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        manager.notify(
            System.currentTimeMillis().toInt(),
            builder.build()
        )

        val vibrator =
            context.getSystemService(Context.VIBRATOR_SERVICE)
                    as Vibrator

        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    500,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(500)
        }
    }
}