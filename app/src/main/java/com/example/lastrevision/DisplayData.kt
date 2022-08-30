package com.example.lastrevision

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DisplayData : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)
        val tvResult=findViewById<TextView>(R.id.tvResult)
        val tvReg=findViewById<TextView>(R.id.tvReg)
        val tvCgpa=findViewById<TextView>(R.id.tvCgpa)
        val tvHonor=findViewById<TextView>(R.id.tvHonor)
        val intent=intent
        val honor=intent.getStringExtra("Honor")
        val reg=intent.getStringExtra("RegNo")
        val cgpa=intent.getStringExtra("CGPA")
        val status=intent.getIntExtra("Status",0)
        if(status==1){
            tvResult.text="Sorry, you are not qualified for a scholarship"
        }else if(status==2){
            tvResult.text="Congratulation, you are qualified for a scholarship"
        }

        tvHonor.text=honor
        tvReg.text=reg
        tvCgpa.text=cgpa

        val btn=findViewById<Button>(R.id.btnInfo)

        btn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ptptn.gov.my"))
            startActivity(i)
        }

    }
}