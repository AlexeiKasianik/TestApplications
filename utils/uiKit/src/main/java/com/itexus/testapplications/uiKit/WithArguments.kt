package com.itexus.testapplications.uiKit

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun Fragment.withArguments(configure: (Bundle) -> Unit) =
    apply { arguments = Bundle().apply(configure) }