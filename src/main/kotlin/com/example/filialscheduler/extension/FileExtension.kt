package com.example.filialscheduler.extension

import java.io.File

fun File.notExists() = !this.exists()