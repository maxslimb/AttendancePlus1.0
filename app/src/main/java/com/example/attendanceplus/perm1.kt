package com.example.attendanceplus

import android.Manifest
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.perm.*

class perm1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perm)
// required location

        per.visibility = View.VISIBLE
        te1.visibility = View.VISIBLE
        per.setOnClickListener {
            Dexter.withContext(applicationContext)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : PermissionListener, MultiplePermissionsListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {

                    }

                    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {

                    }

                })
                .check();

            val locationRequest = LocationRequest.create()?.apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_LOW_POWER
            }

            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
            val client: SettingsClient = LocationServices.getSettingsClient(this)
            val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
            task.addOnSuccessListener {
                val myi = Intent(this@perm1, main::class.java)
                this@perm1.startActivity(myi)
            }

            task.addOnFailureListener { exception ->
                if (exception is ResolvableApiException){
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        val REQUEST_CHECK_SETTINGS =1
                        exception.startResolutionForResult(this@perm1,
                            REQUEST_CHECK_SETTINGS)
                    } catch (sendEx: IntentSender.SendIntentException) {
                        // Ignore the error.
                    }
                }
            }


        }
    }
}