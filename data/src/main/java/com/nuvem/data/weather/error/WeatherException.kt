package com.nuvem.data.weather.error

import java.lang.Exception

class NoNetworkException constructor(error: Throwable): Exception(error)
class ServerUnreachableException constructor(error: Throwable): Exception(error)
class HttpCallFailureException constructor(error: Throwable): Exception(error)
class UnknowNetworkException constructor(error: Throwable): Exception(error)