package id.humaedi.absensi.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.humaedi.absensi.MainActivity
import id.humaedi.absensi.api.RetrofitClient
import id.humaedi.absensi.data.LoginData
import id.humaedi.absensi.data.LoginResponse
import id.humaedi.absensi.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var pref : LoginData
    companion object{
        val context = this
    }
    override fun onStart() {
        super.onStart()
        pref = LoginData(this)
        if(pref.getToken().isNotEmpty()){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = LoginData(this)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.hide()

        binding.button.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            val username = binding.etEmail.text.toString().trim()
//            val password = binding.etPassword.text.toString().trim()
//
//            when {
//                username.isEmpty() -> {
//                    binding.etEmail.error = "Masukkan Email"
//                    binding.etPassword.requestFocus()
//                }
//                password.isEmpty() -> {
//                    binding.etEmail.error = "Massukan Password"
//                    binding .etEmail.requestFocus()
//                } else -> {
//                login(username, password)
//                }
//            }
        }
    }

    private fun login(username: String, password: String){
        RetrofitClient.instance.userLogin(username, password)
            .enqueue(object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.code() == 200) {
                        finish()
                        val result: LoginResponse = response.body()!!
                        val token = result.user.token
                        pref.setToken(token)
                        pref.setLogin(true)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        Toast.makeText(this@LoginActivity, "${pref.getToken()}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "silahkan coba lagi", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(
                    call: Call<LoginResponse>,
                    t: Throwable) {
                }
            })
    }
}