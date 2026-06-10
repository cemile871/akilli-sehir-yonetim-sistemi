# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class com.akillisehirapp.data.model.** { *; }

# Gson
-keep class com.google.gson.** { *; }
-keepattributes Signature
-keepclassmembers class * { @com.google.gson.annotations.SerializedName <fields>; }

# Google Maps
-keep class com.google.android.gms.maps.** { *; }
