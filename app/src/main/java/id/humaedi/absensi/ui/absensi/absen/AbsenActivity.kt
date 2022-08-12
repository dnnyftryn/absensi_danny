package id.humaedi.absensi.ui.absensi.absen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextClock
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.humaedi.absensi.R
import id.humaedi.absensi.databinding.ActivityAbsenBinding
import id.humaedi.absensi.ui.absensi.MapsActivity
import id.humaedi.absensi.ui.absensi.adapter.AbsensiAdapter
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class AbsenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAbsenBinding
    private lateinit var adapter: AbsensiAdapter

    private val viewModel: AbsenViewModel by viewModels()


    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.getAbsensi()
        adapter = AbsensiAdapter()

        binding.rvDaftarAbsensi.adapter = adapter
        binding.rvDaftarAbsensi.layoutManager = LinearLayoutManager(this)

        viewModel.listAbsensi.observe(this) { data ->
            if (data != null) {
                adapter.listAbsensi = data
            }
        }

        val textClock = TextClock(this)
        textClock.format12Hour = "hh:mm:ss a"

        val txtView = findViewById<TextView>(R.id.tv_jam_sekarang)
        txtView.text = textClock.text


        val cal = Calendar.getInstance()

        val sdf = SimpleDateFormat("dd MMM yyyy")

        val f: Format = SimpleDateFormat("EEEE")
        val str: String = f.format(Date())

        val textViewTanggal : TextView = findViewById(R.id.tv_tanggal)
        textViewTanggal.text = ("$str ${sdf.format(cal.time)}")

        val textViewDate : TextView = findViewById(R.id.tv_date)
        textViewDate.text = sdf.format(cal.time)

        backPress()
        clockIn()
        clockOut()
    }

    private fun clockOut() {
        binding.clockOut.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clockIn() {
        binding.clockIn.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }

    private fun backPress() {
        binding.backPress.setOnClickListener {
            onBackPressed()
        }
    }
}