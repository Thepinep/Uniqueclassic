package com.example.uniqueclassic

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.uniqueclassic.Adapter.CustomizedGalleryAdapter
import com.example.uniqueclassic.Adapter.loadImg
import com.example.uniqueclassic.Model.Rent
import com.example.uniqueclassic.Model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class DetailActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var  user : User
    var uid = FirebaseAuth.getInstance().currentUser!!.uid

    private  lateinit var tvTitle: TextView
    private  lateinit var tvLocation: TextView
    private  lateinit var tvPrice: TextView
    private  lateinit var tvPower: TextView
    private  lateinit var tvTransmission: TextView
    private  lateinit var tvFuel: TextView
    private  lateinit var tvSeller: TextView
    private  lateinit var tvVin: TextView
    private  lateinit var tvYear: TextView
    private  lateinit var tvCubic: TextView
    private  lateinit var tvBody: TextView
    private  lateinit var tvKilometre: TextView
    private  lateinit var tvColor: TextView
    private  lateinit var tvCountry: TextView
    private  lateinit var tvWheel: TextView
    private  lateinit var tvUsername: TextView
    private  lateinit var tvDescription: TextView

    private lateinit var  buttonstartdate: Button
    private lateinit var buttonenddate: Button
    private lateinit var  startrent: TextView
    private lateinit var endrent: TextView


    //var etSeller = null
    var currentUserUid = FirebaseAuth.getInstance().currentUser!!.uid
    private lateinit var buyphone: EditText
    private lateinit var buyname :EditText
    private lateinit var etTenant: TextView
    private lateinit var TenantUid: TextView
    private lateinit var title: TextView
    private lateinit var etStartDate: TextView
    private lateinit var etEndDate: TextView
    private lateinit var etEmail: TextView
    private lateinit var etPhone: TextView
    private lateinit var etLocation: TextView
    private lateinit var etCost: TextView
    private lateinit var etButtonDate: TextView
    private lateinit var plntext: TextView






    private lateinit var simpleGallery: Gallery


    private lateinit var customGalleryAdapter: CustomizedGalleryAdapter
    private lateinit var selectedImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        buttonstartdate = findViewById(R.id.ButtonStartDate)
        buttonenddate = findViewById(R.id.ButtonEndDate)
        startrent = findViewById(R.id.startRent)
        endrent = findViewById(R.id.endRent)
        etTenant =findViewById(R.id.user_text)
        etStartDate = findViewById(R.id.startRent)
        title = findViewById(R.id.text_title)
        etEndDate = findViewById(R.id.endRent)
        etLocation = findViewById(R.id.text_Location)
        etCost = findViewById(R.id.cost)
        etButtonDate = findViewById(R.id.ButtonDate)



        dbRef = FirebaseDatabase.getInstance().getReference("Reservations").child(currentUserUid)

        etButtonDate.setOnClickListener {
            saveReservationsData()
        }

        startrent = findViewById(R.id.startRent)
        endrent = findViewById(R.id.endRent)
        plntext = findViewById(R.id.pln_text)

        recalculate()

        val myCalendar = Calendar.getInstance()
        val dataPicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
            recalculate()
        }
        val dataPicker2 = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable2(myCalendar)
            recalculate()
        }

        buttonstartdate.setOnClickListener {
            DatePickerDialog(this, dataPicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        buttonenddate.setOnClickListener {
            DatePickerDialog(this, dataPicker2,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }


        val buttonClick = findViewById<ImageView>(R.id.ButtonBack1)
        buttonClick.setOnClickListener {
            finish()
        }

        initView()

        setValuesToViews()

        userdata()




        simpleGallery = findViewById<View>(R.id.languagesGallery) as Gallery

        // get the reference of ImageView
        selectedImageView = findViewById<View>(R.id.Imageoff) as ImageView


        val storageReference = Firebase.storage.reference
        val list_gallery =intent.getStringArrayExtra("etgalery")?.asList() ?: emptyList()


        loadBig(storageReference, list_gallery[0])

        // initialize the adapter
        customGalleryAdapter = CustomizedGalleryAdapter(applicationContext,storageReference, list_gallery)

        // set the adapter for gallery
        simpleGallery.adapter = customGalleryAdapter

        // Let us do item click of gallery and image can be identified by its position
        simpleGallery.setOnItemClickListener { parent, view, position, id ->
            // Whichever image is clicked, that is set in the  selectedImageView
            // position will indicate the location of image
            loadBig(storageReference ,list_gallery[position])
        }
    }

    private fun userdata() {

        dbRef = FirebaseDatabase.getInstance().getReference("User")

        dbRef.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                user = snapshot.getValue(User::class.java)!!

                val textInputEditTextName = findViewById<TextInputEditText>(R.id.textInputEditBuyName)
                textInputEditTextName.setText(user.etUsername)

                val textInputEditTextPhone = findViewById<TextInputEditText>(R.id.textInputEditBuyPhone)
                textInputEditTextPhone.setText(user.etPhone)

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun loadBig(storageReference: StorageReference, imageUrl: String) {
        selectedImageView.loadImg(this, storageReference , imageUrl)
    }



    private fun recalculate() {
        val date1String = startrent.text.toString()
        val date2String = endrent.text.toString()
        val date1 = SimpleDateFormat("dd-MM-yyyy").parse(date1String)
        val date2 = SimpleDateFormat("dd-MM-yyyy").parse(date2String)
        val diffInDays = TimeUnit.DAYS.convert(date2.time - date1.time, TimeUnit.MILLISECONDS)
        val resultTextView = findViewById<TextView>(R.id.cost)
        val inputText = plntext.text.toString()
        val inputValue = inputText.toInt()
        val result =   inputValue * diffInDays
        if(result >= 0) resultTextView.text = result.toString()
    }


    private fun saveReservationsData() {

        buyphone= findViewById(R.id.textInputEditBuyPhone)
        buyname= findViewById(R.id.textInputEditBuyName)


         val etTenant = etTenant.text.toString()
         val etStartDate = etStartDate.text.toString()
         val etEndDate = etEndDate.text.toString()
         var title = title.text.toString()
         val location = etLocation.text.toString()
         val etCost = etCost.text.toString()
        val buyPhone = buyphone.text.toString()
        val buyName = buyname.text.toString()
        val etPhone = intent.getStringExtra("etPhone")
        val uid = intent.getStringExtra("uid")




        val invoice = dbRef.push().key!!
        val reservations = Rent(
            etTenant = etTenant,
            TenantUid = uid,
            uid = currentUserUid,
            invoice = invoice,
            title = title,
            etStartDate = etStartDate,
            etEndDate = etEndDate,
            etPhone = etPhone,
            location = location,
            etCost = etCost,
            buyName = buyName,
            buyPhone = buyPhone
        )

        if (etStartDate.isNotEmpty() && etEndDate.isNotEmpty() && location.isNotEmpty() && etCost.isNotEmpty()&& buyName.isNotEmpty()&& buyPhone.isNotEmpty()
        ) {
            dbRef.child(invoice).setValue(reservations)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, BookNowScreenActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
        }


    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        startrent.setText(sdf.format(myCalendar.time))

    }
    private fun updateLable2(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        endrent.setText(sdf.format(myCalendar.time))
    }
    private fun initView() {
        tvTitle = findViewById(R.id.text_title)
        tvLocation = findViewById(R.id.text_Location)
        tvPrice = findViewById(R.id.pln_text)
        tvPower = findViewById(R.id.powerdeatal_text)
        tvTransmission = findViewById(R.id.transmission_text)
        tvFuel = findViewById(R.id.fueldetail_text)
        tvSeller = findViewById(R.id.seller_text)
        tvVin = findViewById(R.id.vin_text)
        tvYear = findViewById(R.id.yearproduction_text)
        tvCubic = findViewById(R.id.cubic_text)
        tvBody = findViewById(R.id.body_text)
        tvKilometre = findViewById(R.id.kilometre_text)
        tvColor = findViewById(R.id.color_text)
        tvCountry = findViewById(R.id.home_text)
        tvWheel = findViewById(R.id.wheel_text)
        tvUsername  = findViewById(R.id.user_text)
        tvDescription  = findViewById(R.id.description_text)

    }

    private fun setValuesToViews(){
        tvTitle.text = intent.getStringExtra("etTitle")
        tvLocation.text = intent.getStringExtra("etLocation")
        tvPrice.text = intent.getStringExtra("etPrice")
        tvPower.text = intent.getStringExtra("etPower")
        tvTransmission.text = intent.getStringExtra("etTransmission")
        tvFuel.text = intent.getStringExtra("etFuel")
        tvSeller.text = intent.getStringExtra("etSeller")
        tvVin.text = intent.getStringExtra("etVin")
        tvYear.text = intent.getStringExtra("etYear")
        tvCubic.text = intent.getStringExtra("etCubic")
        tvBody.text = intent.getStringExtra("etBody")
        tvKilometre.text = intent.getStringExtra("etKilometre")
        tvColor.text = intent.getStringExtra("etColor")
        tvCountry.text = intent.getStringExtra("etCountry")
        tvWheel.text = intent.getStringExtra("etWheel")
        tvUsername.text = intent.getStringExtra("etUsername")
        tvDescription.text = intent.getStringExtra("etDescription")


    }
}
