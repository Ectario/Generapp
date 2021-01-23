package com.ectario.generapp.tools

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat

fun changingActivity(contextArg : Context, nextClassActivityArg : Class<*>, bundleArg : Bundle? = null, intentArg : Intent? = null, activityOptionsArg : ActivityOptionsCompat? = null){
    var context = contextArg
    var intent = intentArg
    var nextClassActivity = nextClassActivityArg
    var bundle = bundleArg
    if(intent == null){
        intent = Intent(context, nextClassActivity)
    }
    if(activityOptionsArg != null){
        context.startActivity(intent,activityOptionsArg.toBundle())
        return
    }
    context.startActivity(intent,bundle)
}

fun Context.makeToast(msg : String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}