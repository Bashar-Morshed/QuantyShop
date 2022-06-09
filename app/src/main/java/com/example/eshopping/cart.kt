package com.example.eshopping

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dialogfragment.Feedback

class cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        createNotificationChannel()
        val bundle: Bundle? = intent.extras

        bundle?.let {
            val msg = bundle.getString("key")

            if(msg == "Adidas Men's basic sport T-shirt"){


            }


        }





        val bttn: Button = findViewById(R.id.button3)
        bttn.setOnClickListener {
            var dialog_var=Feedback()
            dialog_var.show(supportFragmentManager, "Custom Dialog")
        }




    }
    private var notificationId1 :Int = 123
    private val channelId = "App_Channel.testNotification"
    private val description = "Trying to test different types notification"

    private  fun notidication1(){

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.fashion_logo)
            .setContentTitle("Quanty Shop")
            .setContentText("Thank you for buying our products")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)){
            notify(notificationId1,builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "test_notification"
            val descriptionText = description
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onStop() {
        notidication1()
        super.onStop()
    }

}