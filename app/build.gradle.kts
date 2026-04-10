plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.profecionalcoursetranslator"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.profecionalcoursetranslator"
        minSdk = 31
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Rx-Java
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    // Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //старые версии:
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.1")
    implementation("com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0")
    //добавила тест:
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    //Koin for Android
    //Current version
    val koinVersion = "3.1.2"
    //Koin core features
    implementation("io.insert-koin:koin-core:$koinVersion")
    //Koin main features for Android (Scope,ViewModel ...)
    implementation("io.insert-koin:koin-android:$koinVersion")
    //Koin Java Compatibility
    implementation("io.insert-koin:koin-android-compat:$koinVersion")
    testImplementation("io.insert-koin:koin-test:${koinVersion}")

    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //Download images
    //Picasso
    implementation("com.squareup.picasso:picasso:2.71828")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    //Coil
    implementation("io.coil-kt:coil:0.11.0")
    //Room
    val room_version = "2.6.1"
    ksp("androidx.room:room-compiler:${room_version}")
    implementation("androidx.room:room-runtime:${room_version}")
    implementation("androidx.room:room-ktx:${room_version}")
}