plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ru.startandroid.feature.catalog"
    compileSdk = 34
}

dependencies {
    implementation(project(":feature:catalog:view"))
    implementation(project(":feature:catalog:data"))
}