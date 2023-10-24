package com.example.submissiondicoding

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.submissiondicoding.api.Result
import com.example.submissiondicoding.databinding.ActivityAddStoryBinding
import com.example.submissiondicoding.di.Injection
import com.example.submissiondicoding.model.LoginViewModel
import com.example.submissiondicoding.model.MainViewModel
import com.example.submissiondicoding.model.ViewModelFactory
import com.example.submissiondicoding.preferences.UserPreference
import kotlinx.coroutines.launch
import java.io.File

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AddStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStoryBinding
    private lateinit var storyViewModel: MainViewModel
    private lateinit var loginViewModel: LoginViewModel
    private var getFile: File? = null

    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "StoryApp needs permissions to run smoothly, please check your app settings",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "New Story"
        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        val userPref = UserPreference.getInstance(dataStore)
        val repository = Injection.provideRepository(this)
        val viewModelFactory = ViewModelFactory(repository, userPref)
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        storyViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun setupAction() {
        binding.btnGallery.setOnClickListener { startGallery() }
        binding.btnCamera.setOnClickListener { startCameraX() }
        binding.btnUpload.setOnClickListener { uploadImage() }
    }

    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val description = binding.edtDesc.text.toString()
            if (description.isEmpty()) {
                binding.edtDesc.error = "Description cannot be empty!"
            } else {
                loginViewModel.readToken().observe(this) { token ->
                    if (token.isNotEmpty()) {
                        storyViewModel.uploadStory(token, description, file)
                        storyViewModel.uploadResult.observe(this) { result ->
                            when (result) {
                                is Result.Loading -> {
                                    binding.progressBar.visibility = View.VISIBLE
                                }
                                is Result.Success -> {
                                    binding.progressBar.visibility = View.INVISIBLE
                                    Toast.makeText(this@AddStoryActivity, "Success upload story", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                                is Result.Error -> {
                                    Toast.makeText(this@AddStoryActivity, "Fail upload story", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    } else {
                        Toast.makeText(this@AddStoryActivity, "Token is empty!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(this@AddStoryActivity, "Image cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result?.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@AddStoryActivity)
                getFile = myFile
                binding.previewImageView.setImageURI(uri)
            }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            myFile?.let { file ->
                rotateFile(file, isBackCamera)
                getFile = file
                binding.previewImageView.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }
}
