package com.sanlorng.kit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import com.google.gson.Gson
import okhttp3.*
import java.net.Inet4Address
import java.net.NetworkInterface

val Context.ipAddress: String?
    get() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected){
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            when(networkCapabilities.connectType){
                NetworkCapabilities.TRANSPORT_CELLULAR -> {
                    for (networkInterface in NetworkInterface.getNetworkInterfaces()) {
                        for (item in networkInterface.inetAddresses)
                            if (item.isLoopbackAddress.not() && item is Inet4Address)
                                return item.hostAddress
                    }
                }
                NetworkCapabilities.TRANSPORT_WIFI -> {
                    val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                    val intIP = wifiManager.connectionInfo.ipAddress
                    return ((intIP and 0xff).toString() + "." + (intIP shr 8 and 0xff) + "."
                            + (intIP shr 16 and 0xff) + "." + (intIP shr 24 and 0xff))

                }
            }
        }
        return null
    }
val Context.isConnected:Boolean
    get() {
        val info = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        return info!= null && info.isConnected
    }
val NetworkCapabilities.connectType:Int?
    get() {
        if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            return NetworkCapabilities.TRANSPORT_WIFI
        if (hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH))
            return NetworkCapabilities.TRANSPORT_BLUETOOTH
        if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            return NetworkCapabilities.TRANSPORT_CELLULAR
        return null
    }
fun OkHttpClient.getRequest(url:String):ResponseBody?{
    return try {
        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        newCall(request).execute().body()
    }catch (e: Exception){
        null
    }
}
val OkHttpClient.mediaJson:MediaType
get() {
    return  MediaType.parse("application/json")!!
}
fun OkHttpClient.postRequest(url:String,mediaType:MediaType?,data:String): ResponseBody?{
    return try {
        val requestBody = RequestBody.create(mediaType,data)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        newCall(request).execute().body()
    }catch (e:Exception){
        e.printStackTrace()
        null
    }
}

    object WebKit{
        val okClient:OkHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { OkHttpClient() }
        val gson:Gson by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { Gson() }
        val mediaJson:MediaType by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MediaType.parse("application/json")!! }
    }