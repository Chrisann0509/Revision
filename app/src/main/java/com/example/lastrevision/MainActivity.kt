package com.example.lastrevision

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.lastrevision.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private var institution=""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        ArrayAdapter.createFromResource(this,R.array.institution,android.R.layout.simple_spinner_item).also {
            adapter->adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerInstitution.adapter=adapter
        }

        binding.spinnerInstitution.onItemSelectedListener=this
        binding.btnSubmit.setOnClickListener {
            getData()
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {

        val item= resources.getStringArray(R.array.institution)[pos]
        institution=item.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun getData(){
        val regNo=binding.etRegNo.text.toString()
        val cgpa=binding.etCGPA.text.toString()
        var honor=""
        var status=1
        val cgpaDec=cgpa.toDouble()
        if(cgpaDec>=3.5 && cgpaDec<=4.0){
            honor="First Class"
            status=2
        }else if(cgpaDec.dec()>=3.0 && cgpaDec.dec()<=3.49999){
            honor="Second Class(Upper Division)"
            status=1
        }else if(cgpaDec>=2.2 && cgpaDec<=2.9999){
            honor= "Second Class(Lower Division)"
            status=1
        }else if(cgpaDec>=3.0 && cgpaDec<=3.49999){
            honor= "Third Class"
            status=1
        }else{
            honor= "Invalid CGPA"
            status=1
        }

        val intent= Intent(this,DisplayData::class.java)
        intent.putExtra("RegNo",regNo)
        intent.putExtra("CGPA",cgpa)
        intent.putExtra("Honor",honor)
        intent.putExtra("Status",status)
        intent.putExtra("Institution",institution)
        startActivity(intent)
    }
}