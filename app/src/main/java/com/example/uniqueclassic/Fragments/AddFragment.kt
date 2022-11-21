package com.example.uniqueclassic.Fragments


import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.ImageAdapter
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R
import com.example.uniqueclassic.databinding.FragmentAddBinding
import com.google.android.material.chip.Chip
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.apache.commons.io.FileUtils.copyInputStreamToFile
import java.io.File


class AddFragment : Fragment() {
    lateinit var btnSelectImages: Button
    lateinit var rvImages: RecyclerView

    private lateinit var binding: FragmentAddBinding
    private lateinit var database : DatabaseReference

    lateinit var imageAdapter: ImageAdapter
    var selectedPaths = mutableListOf<String>()




    override fun onResume() {
        super.onResume()
        val vehicle = resources.getStringArray(R.array.Vehicle)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,vehicle)
        binding.AutoCompleteTextview.setAdapter(arrayAdapter)

        val body = resources.getStringArray(R.array.Body)
        val arrayAdapter2 = ArrayAdapter(requireContext(),R.layout.dropdown_item,body)
        binding.AutoCompleteTextviewBody.setAdapter(arrayAdapter2)

        val color = resources.getStringArray(R.array.Color)
        val arrayAdapter3 = ArrayAdapter(requireContext(),R.layout.dropdown_item,color)
        binding.AutoCompleteTextviewColor.setAdapter(arrayAdapter3)

        val country = resources.getStringArray(R.array.Country)
        val arrayAdapter4 = ArrayAdapter(requireContext(),R.layout.dropdown_item,country)
        binding.AutoCompleteTextviewCountry.setAdapter(arrayAdapter4)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater,container,false)
        imageAdapter = ImageAdapter()
        rvImages.adapter = imageAdapter

        val selectImagesActivityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    //If multiple image selected
                    if (data?.clipData != null) {
                        val count = data.clipData?.itemCount ?: 0

                        for (i in 0 until count) {
                            val imageUri: Uri? = data.clipData?.getItemAt(i)?.uri
                            val file = getImageFromUri(imageUri)
                            file?.let {
                                selectedPaths.add(it.absolutePath)
                            }
                        }
                        imageAdapter.addSelectedImages(selectedPaths)
                    }
                    //If single image selected
                    else if (data?.data != null) {
                        val imageUri: Uri? = data.data
                        val file = getImageFromUri(imageUri)
                        file?.let {
                            selectedPaths.add(it.absolutePath)
                        }
                        imageAdapter.addSelectedImages(selectedPaths)
                    }
                }
            }

        binding.btnSelectImages.setOnClickListener {

            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.type = "*/*"
            selectImagesActivityResult.launch(intent)
        }
        try {
            deleteTempFiles()
        } catch (e: Exception) {

        }




        binding.ButtonAdd.setOnClickListener {
            savedata()
        }

        /*binding.chipGroupChoice.setOnCheckedChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(checkedId)?.test
            Toast.makeText(chipGroup. context , titleOrNull ?: "No Choice" , Toast. LENGTH_LONG ).show()
        }*/



        /*binding.chipGroupChoice.setOnCheckedChangeListener{ group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let {
            }

        }*/
        return binding.root
    }
    private  fun savedata(){

        val etTitle = binding.textInputEditTitle.text.toString()
        val etVehicle = binding.AutoCompleteTextview.text.toString()
        val etDescription = binding.textInputEditDescription.text.toString()
        val etPrice = binding.textInputEditPrice.text.toString()
        val etVin = binding.textInputEditVin.text.toString()
        val etYear = binding.textInputEditYears.text.toString()
        val etPower = binding.textInputEditPower.text.toString()
        val etCubic = binding.textInputEditCubic.text.toString()
        val etBody = binding.AutoCompleteTextviewBody.text.toString()
        val etCountry = binding.AutoCompleteTextviewCountry.text.toString()
        val etPhone = binding.textInputEditPhone.text.toString()


        database = FirebaseDatabase.getInstance().getReference("Directory")

        val etId = database.push().key!!
        val directory = AddModel(etId, etTitle, etVehicle, etDescription, etPrice,  etVin, etYear, etPower, etCubic,etBody,etCountry,etPhone)

        binding.textInputEditTitle.text?.clear()
        binding.AutoCompleteTextview.text.clear()
        binding.textInputEditDescription.text?.clear()
        binding.textInputEditPrice.text?.clear()
        binding.textInputEditVin.text?.clear()
        binding.textInputEditYears.text?.clear()
        binding.textInputEditPower.text?.clear()
        binding.textInputEditCubic.text?.clear()
        binding.AutoCompleteTextviewBody.text?.clear()
        binding.AutoCompleteTextviewCountry.text.clear()
        binding.textInputEditPhone.text?.clear()




        database.child(etId).setValue(directory).addOnCompleteListener {
            Toast.makeText(context, "data",Toast.LENGTH_SHORT).show()



            }.addOnFailureListener{}

    }
    private fun getImageFromUri(imageUri: Uri?) : File? {
        imageUri?.let { uri ->
            val mimeType = getMimeType(requireContext(), uri)
            mimeType?.let {
                val file = createTmpFileFromUri(requireContext(), imageUri,"temp_image", ".$it")
                file?.let { Log.d("image Url = ", file.absolutePath) }
                return file
            }
        }
        return null
    }

    private fun getMimeType(context: Context, uri: Uri): String? {
        val extension: String? = if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            val mime = MimeTypeMap.getSingleton()
            mime.getExtensionFromMimeType(context.contentResolver.getType(uri))
        } else {
            MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(File(uri.path)).toString())
        }
        return extension
    }

    private fun createTmpFileFromUri(context: Context, uri: Uri, fileName: String, mimeType: String): File? {
        return try {
            val stream = context.contentResolver.openInputStream(uri)
            val file = File.createTempFile(fileName, mimeType,cacheDir)
            FileUtils.copyInputStreamToFile(stream, file)
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun deleteTempFiles(file: File = cacheDir): Boolean {
        if (file.isDirectory) {
            val files = file.listFiles()
            if (files != null) {
                for (f in files) {
                    if (f.isDirectory) {
                        deleteTempFiles(f)
                    } else {
                        f.delete()
                    }
                }
            }
        }
        return file.delete()
    }

}
