package com.nuvem.local_provider

import java.lang.Exception

class NoLocalDataFoundExcepetion constructor(error: Throwable): Exception(error)