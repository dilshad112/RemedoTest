package com.kotlinremedo.remedotest

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import android.widget.TabHost
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*



class MainActivity : AppCompatActivity() {
    var currentDate="22/1/2020"
    lateinit var  editText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonHandler()

    }

    fun buttonHandler(){
         editText =findViewById<EditText>(R.id.editText)
        editText.setText(currentDate)
        editText.setOnClickListener(View.OnClickListener {
            showDialog()
        })

        //auto open dialog for demo
        Handler().postDelayed(Runnable {
            showDialog()
        },1000)
    }
    fun tabHostSetup(v:View){
        val tabs = v.findViewById<View>(R.id.tabHost) as TabHost
        tabs.setup()
        var spec = tabs.newTabSpec("tag1")
        spec.setContent(R.id.month)
        spec.setIndicator("Month")
        tabs.addTab(spec)
        spec = tabs.newTabSpec("tag2")
        spec.setContent(R.id.year)
        spec.setIndicator("Year")
        tabs.addTab(spec)
        tabs.setOnTabChangedListener(TabHost.OnTabChangeListener {
            if(it.equals("tag1")) {
                // month picker
            }
            else if(it.equals("tag2")) {
                // year picker


            }
        })
    }
    private fun showDialog() {
        var  bt= BottomSheetDialog(this,R.style.BottomSheetDialogTheme);
         var view= LayoutInflater.from(this).inflate(R.layout.dialog_layout,null);
        var calendarView=view.findViewById<CalendarView>(R.id.calendarView2)
        calendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            var curDate = dayOfMonth.toString()+"/"+month+"/"+year
            editText.setText(curDate)
            bt.dismiss()
        })
        var calendarView2=view.findViewById<CalendarView>(R.id.calendarView3)
        calendarView2.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            var curDate = dayOfMonth.toString()+"/"+month+"/"+year
            editText.setText(curDate)
            bt.dismiss()
        })
        tabHostSetup(view)
        bt.setContentView(view);
        bt.show()

    }

}
