plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.tugassubmission"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tugassubmission"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Shimmering
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    //  MVVM
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //  Fragment
    implementation ("androidx.fragment:fragment-ktx:1.6.1")

    //  Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //  Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.4")

    //  Hilt
    implementation ("com.google.dagger:hilt-android:2.38.1")
    //kapt ("com.google.dagger:hilt-compiler:2.38.1")
    //kapt ("com.google.dagger:dagger-compiler:2.38.1")
    //kapt ("com.google.dagger:dagger-android-processor:2.35.1")

    testImplementation ("com.google.dagger:hilt-android-testing:2.38.1")
    testAnnotationProcessor ("com.google.dagger:hilt-compiler:2.38.1")

    //    Retrofit
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Room
    implementation ("androidx.room:room-runtime:2.5.2")
    //kapt ("androidx.room:room-compiler:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")

    //   Ok Http
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Circle Image View
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.15.0")

    implementation ("androidx.camera:camera-camera2:1.2.3")
    implementation ("androidx.camera:camera-lifecycle:1.2.3")
    implementation ("androidx.camera:camera-view:1.2.3")

    // Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")
}