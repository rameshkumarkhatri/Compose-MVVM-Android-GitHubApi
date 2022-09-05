package com.mobifyall.githubapi.viewmodels

import com.mobifyall.githubapi.core.network.ApiConstants

class HomeViewModel {
}

fun createQueryMap(orgName: String) = mutableMapOf<String, String>().apply {
    put(ApiConstants.KEY_ORDER, ApiConstants.VALUE_DESC)
    put(ApiConstants.KEY_Q, orgName)
    put(ApiConstants.KEY_S, ApiConstants.VALUE_STARS)
    put(ApiConstants.KEY_TYPE, ApiConstants.VALUE_REPOSITORIES)
    put(ApiConstants.KEY_TYPE, ApiConstants.VALUE_ORG)
    put(ApiConstants.KEY_PER_PAGE, ApiConstants.VALUE_DEFAULT_PER_PAGE)
}