plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = libs.versions.applicationId.get()
    compileSdk {
        version = release(libs.versions.compileSdk.get().toInt())
    }

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

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
    implementation(project(":core"))
    implementation(project(":repository"))
    implementation(project(":model"))
    implementation(project(":utils"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Rx-Java
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    /*// Retrofit 2
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)
    //старые версии:
    implementation(libs.logging.interceptor)*/
    implementation(libs.retrofit2.rxjava2.adapter)
    //добавила тест:
    testImplementation("junit:junit:4.+")
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)
    //Koin for Android
    //Koin core features
    implementation(libs.koin.core)
    //Koin main features for Android (Scope,ViewModel ...)
    implementation(libs.koin.android)
    //Koin Java Compatibility
    implementation(libs.koin.android.compat)
    testImplementation(libs.koin.test)

    //Download images
    //Picasso
    implementation(libs.picasso)
    //Glide
    implementation(libs.glide)
    implementation(libs.androidx.swiperefreshlayout)
    //Coil
    implementation(libs.coil)
    //Room
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
}