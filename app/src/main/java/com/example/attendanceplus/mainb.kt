package com.example.attendanceplus

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.mainb.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket
import java.net.UnknownHostException

class mainb : AppCompatActivity() {
    var response = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainb)
        att.setOnClickListener {
            MyClientTask(this).execute()
        }
        con.setOnClickListener { wifion() }
    }

    protected fun wifion() {
        val ssid = "AndroidShare"
        val pt = PatternMatcher(ssid, 1)
        var builder: WifiNetworkSpecifier.Builder? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            builder = WifiNetworkSpecifier.Builder()
            builder.setSsidPattern(pt)
            builder.setWpa2Passphrase(code!!.text.toString())
            val wifiNetworkSpecifier = builder.build()
            val networkRequestBuilder1 = NetworkRequest.Builder()
            networkRequestBuilder1.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            networkRequestBuilder1.setNetworkSpecifier(wifiNetworkSpecifier)
            val networkRequest = networkRequestBuilder1.build()
            val networkCallback: NetworkCallback
            val context = applicationContext
            val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            networkCallback = object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    Log.d(TAG, "onAvailable:$network")
                    cm.bindProcessToNetwork(network)
                }
            }
            cm.requestNetwork(networkRequest, networkCallback)
        }
    }

    @SuppressLint("StaticFieldLeak")
     inner class MyClientTask internal constructor(mainb: mainb) : AsyncTask<Void?, Void?, Void?>() {
        var dstAddress = "192.168.43.1"
        var dstPort = 8081
        protected override fun doInBackground(vararg p0: Void?): Void? {
            var socket: Socket? = null
            var dataOutputStream: DataOutputStream? = null
            var dataInputStream: DataInputStream? = null
            val roll = "2018HE0263 "
            val name = "Kishan Patel"
            try {
                socket = Socket(dstAddress, dstPort)
                dataOutputStream = DataOutputStream(socket.getOutputStream())
                dataInputStream = DataInputStream(socket.getInputStream())
                dataOutputStream.writeUTF(roll + name)
                response = dataInputStream.readUTF()
            } catch (e: UnknownHostException) {
                e.printStackTrace()
                response = "UnknownHostException: $e"
            } catch (e: IOException) {
                e.printStackTrace()
                response = "IOException: $e"
            } finally {
                if (socket != null) {
                    try {
                        socket.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            when (response) {
                "404" -> {
                }
                "200" -> Toast.makeText(this@mainb, "Success Attendance", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this@mainb, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
            super.onPostExecute(result)
        }
    }

    companion object {
        private val TAG = main::class.java.simpleName
    }
}