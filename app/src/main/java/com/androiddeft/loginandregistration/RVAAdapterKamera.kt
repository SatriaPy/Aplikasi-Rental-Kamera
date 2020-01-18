package com.androiddeft.loginandregistration

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.student_list.view.*

class RVAAdapterKamera(private val context: Context, private val arrayList: ArrayList<Kamera>) : RecyclerView.Adapter<RVAAdapterKamera.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.student_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lbNimList.text = "ID Kamera : "+arrayList?.get(position)?.idCamera
        holder.view.lbNameList.text = "Nama Kamera : "+arrayList?.get(position)?.nameCamera
        holder.view.lbTAnggal.text = "Tanggal Pinjam : "+arrayList?.get(position)?.Tanggal
        holder.view.lbStatus.text = "Status Pinjam : "+arrayList?.get(position)?.Status

        holder.view.cvList.setOnClickListener {

            val i = Intent(context,ManageRentalActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("id",arrayList?.get(position)?.idCamera)
            i.putExtra("name",arrayList?.get(position)?.nameCamera)
            i.putExtra("Tanggal",arrayList?.get(position)?.Tanggal)
            i.putExtra("Status",arrayList?.get(position)?.Status)
            context.startActivity(i)

        }

    }

    class Holder(val view:View) : RecyclerView.ViewHolder(view)

}