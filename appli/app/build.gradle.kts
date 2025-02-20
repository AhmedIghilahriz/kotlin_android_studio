plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.appli"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appli"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.2.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.")
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("io.github.plotlydev:kotlin-plot:0.9.0")
    implementation ("io.github.kotlin-graphics:plot:0.8.1")
    implementation ("com.opencsv:opencsv:5.2")
}
