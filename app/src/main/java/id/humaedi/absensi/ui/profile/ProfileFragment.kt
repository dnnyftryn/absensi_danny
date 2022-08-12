package id.humaedi.absensi.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import id.humaedi.absensi.api.RetrofitClient
import id.humaedi.absensi.data.LoginData
import id.humaedi.absensi.data.RegistResponse
import id.humaedi.absensi.databinding.FragmentProfileBinding
import id.humaedi.absensi.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private lateinit var pref : LoginData
    companion object{
        val context = this
    }

    private var _Binding: FragmentProfileBinding? = null
    private val Binding: FragmentProfileBinding get() = _Binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        _Binding = FragmentProfileBinding.inflate(inflater, container, false)
        return Binding.root
    }

    private fun logout() {
        pref = LoginData(requireActivity())
        val token = pref.getToken()
        val call = RetrofitClient.instance.userLogout("Bearer $token")
        call.enqueue(object : Callback<RegistResponse> {
            override fun onResponse(call: Call<RegistResponse>, response: Response<RegistResponse>) {
                if (response.headers().get("Authorization") != null) {
                    requireActivity().finish()
                    pref.removeData()
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireActivity(), "Logout Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegistResponse>, t: Throwable) {
                Toast.makeText(activity, "Error $token", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Binding.logout.setOnClickListener {
            logout()
        }
        Binding.backProfile.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
