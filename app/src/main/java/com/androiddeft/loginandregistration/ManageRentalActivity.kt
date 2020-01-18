package com.androiddeft.loginandregistration

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_manage_kamera.*
import org.json.JSONObject

class ManageRentalActivity : AppCompatActivity() {

    lateinit var i:Intent
    private var gender = "Pinjam"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_kamera)

        i = intent

        if(i.hasExtra("editmode")){

            if(i.getStringExtra("editmode").equals("1")){

                onEditMode()

            }

        }

        rgGender.setOnCheckedChangeListener { radioGroup, i ->

            when(i){

                R.id.radioBoy->{
                    gender = "Pinjam"
                }

                R.id.radioGirl->{
                    gender = "Kembali"
                }

            }

        }

        btnCreate.setOnClickListener {
            create()
        }

        btnUpdate.setOnClickListener {
            update()
        }

        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Hapus data ini ?")
                    .setPositiveButton("HAPUS",DialogInterface.OnClickListener { dialogInterface, i ->
                        delete()
                    })
                    .setNegativeButton("BATAL",DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    })
                    .show()
        }

    }

    private fun onEditMode(){

        txId.setText(i.getStringExtra("nim"))
        txName.setText(i.getStringExtra("name"))
        txTanggals.setText(i.getStringExtra("tanggal"))
        txStatus.isEnabled = false

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE

        gender = i.getStringExtra("gender")

        if(gender.equals("Pinjam")){
            rgGender.check(R.id.radioBoy)
        }else{
            rgGender.check(R.id.radioGirl)
        }

    }

    private fun create(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
                .addBodyParameter("id",txId.text.toString())
                .addBodyParameter("namecamera",txName.text.toString())
                .addBodyParameter("tanggal",txTanggals.text.toString())
                .addBodyParameter("status",txStatus.text.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        loading.dismiss()
                        Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                        if(response?.getString("message")?.contains("successfully")!!){
                            this@ManageRentalActivity.finish()
                        }

                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ONERROR",anError?.errorDetail?.toString())
                        Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


                })

    }

    private fun update(){

        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.UPDATE)
                .addBodyParameter("nim",txId.text.toString())
                .addBodyParameter("name",txName.text.toString())
                .addBodyParameter("address",txTanggals.text.toString())
                .addBodyParameter("gender",txStatus.text.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        loading.dismiss()
                        Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                        if(response?.getString("message")?.contains("successfully")!!){
                            this@ManageRentalActivity.finish()
                        }

                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ONERROR",anError?.errorDetail?.toString())
                        Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


                })

    }

    private fun delete(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint.DELETE+"?id="+txId.text.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        loading.dismiss()
                        Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                        if(response?.getString("message")?.contains("successfully")!!){
                            this@ManageRentalActivity.finish()
                        }

                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ONERROR",anError?.errorDetail?.toString())
                        Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


                })

    }


}
