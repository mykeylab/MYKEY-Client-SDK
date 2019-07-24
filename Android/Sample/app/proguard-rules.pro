# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-ignorewarnings
-verbose
-dontskipnonpubliclibraryclasses
-dontusemixedcaseclassnames
-optimizationpasses 5
-keepattributes *Annotation*,InnerClasses
-keepattributes SourceFile,LineNumberTable
-optimizations !code/simplification/cast,!field/*,!class/merging/*

-dontwarn javax.annotation.**
-dontwarn javax.inject.**

-keepattributes Signature
-keepattributes Exceptions

-dontwarn sun.misc.**

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

#
-dontwarn org.picketbox.**
-keep class org.picketbox.** { *;}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-keep public class javax.**
-keep public class android.webkit.**

-keep class android.support.**{*;}
-dontwarn android.support.v4.**

-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-dontwarn com.google.**

-dontwarn com.android.**

-keep class **Entity{*;}
-dontwarn **Entity


-keep class com.mykey.sdk**{*;}
-dontwarn com.mykey.sdk**

-keep class go**{*;}
-dontwarn go**

-keep class mykeycore**{*;}
-dontwarn mykeycore**
