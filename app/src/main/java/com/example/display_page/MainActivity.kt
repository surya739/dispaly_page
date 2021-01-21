package com.example.display_page

import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog.OnDateSetListener
import com.example.display_page.MyModel
import com.example.display_page.MyAdapter
import androidx.viewpager.widget.ViewPager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.display_page.R
import com.example.display_page.dataPickerFragment
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity(), OnDateSetListener {
    private val price: String? = null
    private var actionBar: ActionBar? = null
    private var modelArrayList: ArrayList<MyModel>? = null
    private var myadapter: MyAdapter? = null
    private var viewPager: ViewPager? = null

    var weekprice: Int?=null

    var priceamt: Int?=null

    var per_week: Button? = null
    var per_day: Button? = null
    var per_month: Button? = null
    var sliderDotspanel: LinearLayout? = null
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Priceamount = findViewById<TextView>(R.id.priceamount) as TextView

        per_day = findViewById(R.id.per_day)
        actionBar = supportActionBar
        viewPager = findViewById(R.id.image)
        sliderDotspanel = findViewById<View>(R.id.Slidedots) as LinearLayout
        per_week = findViewById(R.id.per_week)
        per_month = findViewById(R.id.per_month)


        val calender = findViewById<ImageView>(R.id.calender)
        calender.setOnClickListener {
            val datapicker: DialogFragment = dataPickerFragment()
            datapicker.show(supportFragmentManager, "date picker")
        }

        loadcards()

        per_week!!.setOnClickListener(View.OnClickListener {
            priceamt = Priceamount!!.text.toString().toIntOrNull()
            weekprice = priceamt!! * 7
            Priceamount!!.text = weekprice.toString()
            Log.d("qwe","this is $priceamt")
            Log.d("qwe","this is $weekprice")

        })

        per_day!!.setOnClickListener(View.OnClickListener {


            priceamt = Priceamount!!.text.toString().toIntOrNull()
            weekprice = priceamt!! * 1
            Priceamount!!.text = weekprice.toString()
            Log.d("qwe","this is $priceamt")
            Log.d("qwe","this is $weekprice")

        })

        per_month!!.setOnClickListener(View.OnClickListener {




            priceamt = Priceamount!!.text.toString().toIntOrNull()
            weekprice = priceamt!! * 30
            Priceamount!!.text = weekprice.toString()
            Log.d("qwe","this is $priceamt")
            Log.d("qwe","this is $weekprice")
        })


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
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
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
        modelArrayList!!.add(MyModel(R.drawable.shoeimage))
        modelArrayList!!.add(MyModel(R.drawable.photo1))
        modelArrayList!!.add(MyModel(R.drawable.photo2))
        modelArrayList!!.add(MyModel(R.drawable.photo2))
        myadapter = MyAdapter(this, modelArrayList!!)
        viewPager!!.adapter = myadapter
    }
}