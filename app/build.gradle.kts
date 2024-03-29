plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
   //id ("kotlin-kapt")
}

android {
    namespace = "com.example.minhaloja"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.minhaloja"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.github.chrisbanes:PhotoView:2.3.0")
    implementation ("com.github.bumptech.glide:glide:4.11.0")

    implementation ("com.github.clans:fab:1.6.4")
    implementation ("com.google.code.gson:gson:2.10")

//    implementation ("com.google.dagger:hilt-android:2.38.1")
//    kapt ("com.google.dagger:hilt-android-compiler:2.38.1")

    //implementation ("co.ankurg.expressview:expressview:0.0.3")

    implementation ("com.github.jd-alexander:LikeButton:0.2.3")


}