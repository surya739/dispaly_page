package com.example.display_page

import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity(), OnDateSetListener {
    private val price: String? = null
    private var actionBar: ActionBar? = null
    private var modelArrayList: ArrayList<MyModel>? = null
    private var myadapter: MyAdapter? = null
    private var ViewPager: ViewPager? = null
    var sliderDotspanel: LinearLayout? = null
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar = supportActionBar
        ViewPager = findViewById(R.id.image)
        sliderDotspanel = findViewById<View>(R.id.Slidedots) as LinearLayout
        val calender = findViewById<ImageView>(R.id.calender)
        calender.setOnClickListener {
            val datapicker: DialogFragment = dataPickerFragment()
            datapicker.show(supportFragmentManager, "date picker")
        }
        loadcards()
        val genres1 = findViewById<Spinner>(R.id.generss)
        val items = arrayOf("Per Day", "Per week", "Per month")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        genres1.adapter = adapter
        dotscount = myadapter!!.count
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dots))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel!!.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dots))


        ViewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dots))
                }
                dots[position]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dots))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c[Calendar.YEAR] = year
        c[Calendar.MONTH] = month
        c[Calendar.DAY_OF_MONTH] = dayOfMonth
        val CurrentDatestring = DateFormat.getDateInstance(DateFormat.FULL).format(c.time)
        val date = findViewById<TextView>(R.id.date)
        date.text = CurrentDatestring
    }

    private fun loadcards() {
        modelArrayList = ArrayList()
        modelArrayList!!.add(MyModel(R.drawable.photo))
        modelArrayList!!.add(MyModel(R.drawable.photo1))
        modelArrayList!!.add(MyModel(R.drawable.photo2))
        modelArrayList!!.add(MyModel(R.drawable.photo2))
        myadapter = MyAdapter(this, modelArrayList!!)
        ViewPager!!.adapter = myadapter
        ViewPager!!.setPadding(0, 0, 0, 0)
    }
}