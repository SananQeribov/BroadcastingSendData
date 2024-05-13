package com.legalist.broadcastingsenddata

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        sendData()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    private fun sendData() {
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, BroadcastDatasend::class.java)
            val bundle = Bundle().apply {
                putString("Name", "Sanan")
                putInt("age", 22)
                putString("Job", "Programmer")
            }
            intent.putExtras(bundle)
            sendBroadcast(intent)
        }
    }

    class BroadcastDatasend : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val bundle = intent?.extras

            val name = bundle?.getString("Name")
            val age = bundle?.getInt("age")
            val job = bundle?.getString("Job")

            Toast.makeText(
                context,
                "Name: $name, Age: $age, Job: $job",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
