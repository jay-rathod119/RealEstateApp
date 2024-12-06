import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.solutelabs.realestateapp.R
import com.solutelabs.realestateapp.databinding.FragmentLoginBinding
import com.solutelabs.realestateapp.fragment.BaseFragment
import com.solutelabs.realestateapp.fragment.ForgotPasswordFragment
import com.solutelabs.realestateapp.fragment.SignupFragment
import com.solutelabs.realestateapp.util.GoogleLoginUtils
import com.solutelabs.realestateapp.util.replaceFragment

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure Google Sign-In options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        // Initialize Firebase Authentication instance
        firebaseAuth = FirebaseAuth.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.TextViewRegister.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                SignupFragment.getInstance(), SignupFragment::class.java.name
            )
        }

        binding.TextViewForgotPassword.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                ForgotPasswordFragment.getInstance(), ForgotPasswordFragment::class.java.name
            )
        }

        binding.ImageViewGoogle.setOnClickListener {
            GoogleLoginUtils.googleSignIn(googleSignInClient, this)
            val username = GoogleLoginUtils.getUserName()
            Toast.makeText(requireContext(),"welcome to $username",Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        GoogleLoginUtils.handleGoogleSignInResult(requestCode, resultCode, data, firebaseAuth, this)
    }

    companion object {
        fun getInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}
