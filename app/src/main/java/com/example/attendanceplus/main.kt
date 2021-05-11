

package com.example.attendanceplus

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.net.wifi.WifiManager.LocalOnlyHotspotCallback
import android.net.wifi.WifiManager.LocalOnlyHotspotReservation
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.BasePermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.main.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.util.*

class main : AppCompatActivity() {
    private var mReservation: LocalOnlyHotspotReservation? = null
    var serverSocket: ServerSocket? = null
    var rollNoList: ArrayList<String?>? = null
    var rollNo = ""
    var adapter: ArrayAdapter<*>? = null
    var socketServerThread: Thread? = null
    var t: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        imei.text = "SUCCESS"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            turnOnHotspot()
        }
        t = findViewById(R.id.imei)
        val clientList = findViewById<ListView>(R.id.lv_client_list)
        rollNoList = ArrayList()
        adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1,
            rollNoList!! as List<Any?>
        )
        clientList.adapter = adapter
        main()
       st.setOnClickListener{
            stopServer()
            turnOffHotspot()
           startActivity(Intent(this@main, Home_Screen::class.java))
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun turnOnHotspot() {
        val manager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        manager.startLocalOnlyHotspot(object : LocalOnlyHotspotCallback() {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun onStarted(reservation: LocalOnlyHotspotReservation) {
                super.onStarted(reservation)
                Log.e(TAG, "Wifi Hotspot is on now")
                imei.text = reservation.wifiConfiguration!!.preSharedKey
                //reservation.softApConfiguration.passphrase?.let { Log.d("jjkjjjj ", it) }
            }

            override fun onStopped() {
                super.onStopped()
                Log.e(TAG, "onStopped: ")
            }

            override fun onFailed(reason: Int) {
                super.onFailed(reason)
                Log.d(TAG, "onFailed: ")
            }
        }, Handler())
    }

    private fun turnOffHotspot() {
        if (mReservation != null) {
            mReservation!!.close()
        }
        Log.e(TAG, "Wifi Hotspot is stopped")
    }

    private fun stopServer() {
        if (serverSocket != null) {
            try {
                socketServerThread!!.interrupt()
                serverSocket!!.close()
                Toast.makeText(this@main, "ServerSocket CLOSED", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun startServer() {
        socketServerThread = Thread(SocketServerThread())
        socketServerThread!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (serverSocket != null) {
            try {
                socketServerThread!!.interrupt()
                serverSocket!!.close()
                Toast.makeText(this@main, "ServerSocket CLOSED", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        turnOffHotspot()
    }

    private inner class SocketServerThread : Thread() {
        override fun run() {
            var socket: Socket? = null
            var dataInputStream: DataInputStream? = null
            var dataOutputStream: DataOutputStream? = null
            try {
                val serverSocket = ServerSocket(Companion.SOCKET_SERVER_PORT)
                while (true) {
                    socket = serverSocket.accept()
                    dataInputStream = DataInputStream(socket.getInputStream())
                    dataOutputStream = DataOutputStream(socket.getOutputStream())
                    rollNo = dataInputStream.readUTF()
                    rollNoList!!.add(rollNo)
                    runOnUiThread { adapter!!.notifyDataSetChanged() }
                    dataOutputStream.writeUTF("200")
                    Log.e(TAG, "Connected")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (socket != null) {
                    try {
                        socket.close()
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
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

    }

    companion object {
        private val TAG = main::class.java.simpleName
        const val SOCKET_SERVER_PORT = 8081
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            startServer()
        }

    }
}