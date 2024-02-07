package com.saavatech.jetpackauthentication.domain.model

import com.saavatech.jetpackauthentication.data.models.Data
import com.saavatech.jetpackauthentication.data.models.Support

data class User(
    var data: Data,
    var support: Support
)
