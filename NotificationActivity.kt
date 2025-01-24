package com.example.mplcricket
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.mplcricket.RecyclerViewAdapter.NotificationRecyclerViewAdapter
import com.example.mplcricket.gtestrclasses.NotificationOfAddingInTeamDataClass
import com.google.android.material.snackbar.Snackbar

class NotificationActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setSupportActionBar(findViewById(R.id.notification_app_bar_layout))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "    Notification"


        LoadNotification()
    }


    fun LoadNotification(){
        Snackbar.make(findViewById(R.id.notification_activity_layout),"Wait..", Snackbar.LENGTH_INDEFINITE).show()
        val notification_recycler_view=findViewById<RecyclerView>(R.id.notification_recycler_view)
        val myRef= FirebaseDatabase.getInstance().reference.child("Users").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("notification").ref
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                var arrayList:ArrayList<NotificationOfAddingInTeamDataClass> = ArrayList()
                for (postSnapshot: DataSnapshot in p0.children) {
                    var p1=postSnapshot.getValue(NotificationOfAddingInTeamDataClass::class.java)
                    if (p1 != null) {
                        arrayList.add(p1)
                    }
                }
                notification_recycler_view.layoutManager= LinearLayoutManager(applicationContext, LinearLayout.VERTICAL,false)
                notification_recycler_view.adapter= NotificationRecyclerViewAdapter(arrayList,applicationContext,myRef)
                Snackbar.make(findViewById(R.id.notification_activity_layout),"Done..", Snackbar.LENGTH_SHORT).show()
            }
        })

    }
}
