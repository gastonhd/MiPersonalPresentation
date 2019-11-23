package com.gastondominguez.mipersonalpresentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gastondominguez.mipersonalpresentation.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var db = FirebaseFirestore.getInstance()
    private var id = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            db.collection("users").get().addOnSuccessListener { result ->
                for (document in result) {
                    id = document.id
                    val user = document.toObject(User::class.java)
                    nameValue.text = user.name
                    addressValue.text = user.adrress
                    ageValue.text = user.age.toString()
                    phoneValue.text = user.phone
                    lastNameValue.text = user.lastName

                }

            }.addOnFailureListener {

            }

        }


        saveInfo.setOnClickListener {
            val washingtonRef = db.collection("users").document(id)
            washingtonRef.update("sex", sexValue.text.toString()).addOnSuccessListener {
                Log.d("update", "success")

            }.addOnFailureListener { Log.d("update", "failed") }

        }

    }
}
