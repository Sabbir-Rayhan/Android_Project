package com.example.mplcricket

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.Button
import android.widget.RelativeLayout
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.GoogleAuthProvider


class Signup_Activity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private  var RC_SIGN_IN = 1
    private  var TAG = ""
    lateinit var go_to_login_page:RelativeLayout
    lateinit var sign_up_with_google:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        go_to_login_page.setOnClickListener {
            startActivity(Intent(this,Login_Activity::class.java))

        }

        sign_up_with_google.setOnClickListener {
            signIn()
        }

        mAuth = FirebaseAuth.getInstance()

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(this.TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }

    }

//    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
//         Log.d(this.TAG, "firebaseAuthWithGoogle:" + acct.id!!)
//         val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//         mAuth?.signInWithCredential(credential)
//                 ?.addOnCompleteListener(this) { task ->
//                     if (task.isSuccessful) {
//                         // Sign in success, update UI with the signed-in user's information
//                         Log.d(this.TAG, "signInWithCredential:success")
//                         Snackbar.make(findViewById(R.id.activity_signup)," welcome"+ acct.displayName.toString(), Snackbar.LENGTH_SHORT).show()
//                         updateUI(acct)
//                     } else {
//                         // If sign in fails, display a message to the user.
//                         Log.w(this.TAG, "signInWithCredential:failure", task.exception)
//                        Snackbar.make(findViewById(R.id.activity_signup), "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
//                         updateUI(null)
//                     }
//
//                     // ...
//                 }
//     }

    @SuppressLint("ShowToast")
    private fun updateUI(account: GoogleSignInAccount?){
        if (account==null){
            Toast.makeText(this,"error in login",Toast.LENGTH_SHORT)
        }
        else{
            val sharePref = SharedPreferenceDataClass.getInstance(this)
            sharePref.setValue("email",account.email.toString())
            sharePref.setValue("name",account.displayName.toString())
            sharePref.setValue("img_url",account.photoUrl.toString())
            startActivity(Intent(this,After_Signup_Activity::class.java))

        }

    }
}
