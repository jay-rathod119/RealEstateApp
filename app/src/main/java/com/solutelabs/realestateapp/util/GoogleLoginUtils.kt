package com.solutelabs.realestateapp.util

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.solutelabs.realestateapp.fragment.HomeFragment
object GoogleLoginUtils {

    private const val RC_SIGN_IN = 9001
    private var userDisplayName: String = ""

    fun googleSignIn(googleSignInClient: GoogleSignInClient, fragment: Fragment) {
        val signInIntent = googleSignInClient.signInIntent
        fragment.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun googleSignOut(googleSignInClient: GoogleSignInClient, fragment: Fragment) {
        googleSignInClient.signOut()
            .addOnCompleteListener(fragment.requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Logout successful, update UI accordingly
                    // For example, show the login screen or navigate to a different fragment/activity
                    Toast.makeText(
                        fragment.requireContext(),
                        "Logged out successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Logout failed, display a message to the user
                    Toast.makeText(fragment.requireContext(), "Logout failed.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    fun handleGoogleSignInResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        firebaseAuth: FirebaseAuth,
        fragment: Fragment
    ) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account, firebaseAuth, fragment)
                }
            } catch (e: ApiException) {
                Toast.makeText(
                    fragment.requireContext(),
                    "Google Sign-In failed: ${e.statusCode}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(
        account: GoogleSignInAccount,
        firebaseAuth: FirebaseAuth,
        fragment: Fragment
    ) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(fragment.requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    userDisplayName = user?.displayName ?: ""
                    fragment.requireActivity().supportFragmentManager.replaceFragment(
                        HomeFragment.getInstance(), HomeFragment::class.java.name
                    )
                } else {
                    Toast.makeText(
                        fragment.requireContext(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun getUserName(): String {
        return userDisplayName
    }
}
