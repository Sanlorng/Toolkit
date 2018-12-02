package com.sanlorng.kit

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.replace(fragmentContainerResID:Int,fragment:Fragment):FragmentTransaction{
    val transaction = beginTransaction()
    transaction.replace(fragmentContainerResID,fragment)
    return transaction
}
fun FragmentManager.addFragment(fragmentContainerResID:Int,fragmentList:List<Fragment>):FragmentTransaction{
    val transaction = this.beginTransaction()
    fragmentList.forEach {
        transaction.add(fragmentContainerResID,it,it.javaClass.name)
        transaction.hide(it)
    }
    return transaction
}
fun FragmentManager.switchFragment(targetFragment:Fragment):FragmentTransaction {

    val transaction = this.beginTransaction()
    currentFragment?.run {
        transaction.hide(this)
    }
    currentFragment = targetFragment
    transaction.show(targetFragment)
    return transaction
}
var FragmentManager.currentFragment:Fragment?
    get() {
        return FragmentKit.currentFragment
    }
    set(value) {
        FragmentKit.currentFragment = value
    }
object FragmentKit{
    var currentFragment:Fragment? = null
}