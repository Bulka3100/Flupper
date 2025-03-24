plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

        //тут добавил чтобы отображался kapt что и зачем?
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.flupperapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.flupperapp"
        minSdk = 24
        targetSdk = 35
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
   implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compile:2.5.2")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation (libs.androidx.fragment.ktx)
    implementation (libs.androidx.recyclerview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}