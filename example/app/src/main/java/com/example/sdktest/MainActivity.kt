package com.example.sdktest

import android.R.attr.bitmap
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.twyn.sdk.backend.TwynTransactions
import br.com.twyn.sdk.listeners.BackendTransactionListener
import br.com.twyn.sdk.listeners.EnrollFaceListener
import br.com.twyn.sdk.listeners.EnrollFingerListener
import br.com.twyn.sdk.listeners.VerifFaceListener
import br.com.twyn.sdk.ui.FaceActivity
import br.com.twyn.sdk.ui.FaceVerifActivity
import br.com.twyn.sdk.ui.FingerActivity
import br.com.twyn.sdk.ui.helpers.EnrollFaceResult
import br.com.twyn.sdk.ui.helpers.EnrollFingersResult
import br.com.twyn.sdk.ui.helpers.VerifFaceResult
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() , EnrollFaceListener, EnrollFingerListener,
    VerifFaceListener, BackendTransactionListener {

    //Store base64 face data
    private var l__faceData: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)




        val verButton = findViewById<Button>(R.id.btnVerif)
        verButton.setOnClickListener { //Create person
            FaceVerifActivity.setVerifFaceListener(this)
	    //Put the Person Id to  be verified
	    myIntent.putExtra("personId", "testID")
            val myIntent = Intent(this@MainActivity, FaceVerifActivity::class.java)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)

            startActivity(myIntent)
        }
        val enrButton = findViewById<Button>(R.id.btnEnroll)
        enrButton.setOnClickListener {

            FaceActivity.setEnrollFaceListener(this)
            val myIntent = Intent(this@MainActivity, FaceActivity::class.java)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)

            startActivity(myIntent)


        }


    }


    override fun onEnrollFaceCompleted(enrollFaceResult: EnrollFaceResult?) {
        println("On Enroll Face Completed !!!")
        if (enrollFaceResult != null) {
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "Successful Face Enrollment. Liveness Score ${enrollFaceResult.livenessScore}",
                    Toast.LENGTH_LONG
                ).show()
            }

            //Save base64 data
            l__faceData = encodeImage(enrollFaceResult.croppedFace)


            //Call fingerprint enrollment
            FingerActivity.setEnrollFingerListener(this)
            val myIntent = Intent(this@MainActivity, FingerActivity::class.java)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            startActivity(myIntent)


        }
    }

    override fun onEnrollFingerCompleted(enrollFingersResult: EnrollFingersResult) {
        val keys: Set<*> = enrollFingersResult.fingersMap.keys
        println("keys $keys")

        val i = keys.iterator()
        while (i.hasNext()) {
            val key = i.next() as String
            println("key $key")
            val value = enrollFingersResult.fingersMap[key]
            println("value $value")

        }

        runOnUiThread {
            Toast.makeText(
                this@MainActivity,
                "Left Hand Score  ${enrollFingersResult.leftHandScore} \n Right Hand Score ${enrollFingersResult.rightHandScore}",
                Toast.LENGTH_LONG
            ).show()
        }


        //Call Backend
        val txtName = findViewById<EditText>(R.id.txtName) as EditText
        val txtNickname = findViewById<EditText>(R.id.txtNickName) as EditText
        val txtEmail = findViewById<EditText>(R.id.txtEmail) as EditText
        val txtPhone = findViewById<EditText>(R.id.txtMob) as EditText
        val idn =
            "WWW/RBKSswT70VKGxsRauYdZUfh4Br8KNTCgQqdZW7acBwNyzyS5MfJWEzsudmYFQLvNCgAIMmRqWYpp9W93UQ=="
        val twynTransaction = TwynTransactions(applicationContext);
        twynTransaction.setBackendTransactionListener(this);
        twynTransaction.EnrollPerson(<app-id>,<api-key>,idn, txtName.text.toString(), txtNickname.text.toString(), txtEmail.text.toString(),txtPhone.text.toString(), l__faceData )

    }

    override fun onTransactionCompleted(p0: String?) {
        runOnUiThread {
            Toast.makeText(
                this@MainActivity,
                "Successful Backend Communication!",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun onTransactionFailed(p0: String?) {
        TODO("Not yet implemented")
    }

    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    override fun onVerifFaceCompleted(p0: VerifFaceResult?) {
        runOnUiThread {
            Toast.makeText(
                this@MainActivity,
                "Verif Score ${p0?.verifScore} \n Verif Decision ${p0?.verifDecision}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
