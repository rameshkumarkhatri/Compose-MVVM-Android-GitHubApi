package com.mobifyall.githubapi.core.network

class GithubException( simpleMessage: String, val errorCode: Int = 0) : Exception(simpleMessage)