package com.example.bookbox.util

import retrofit2.HttpException
import java.io.IOException

fun Throwable.toUserMessage(): String = when (this) {
    is IOException -> "Gagal terhubung. Periksa koneksi internet Anda."
    is HttpException -> "Server bermasalah (kode ${code()}). Coba lagi nanti."
    else -> "Terjadi kesalahan: ${message ?: "tidak diketahui"}"
}

private val languageNames = mapOf(
    "eng" to "Inggris", "ind" to "Indonesia", "ger" to "Jerman",
    "fre" to "Prancis", "spa" to "Spanyol", "dut" to "Belanda",
    "jpn" to "Jepang", "chi" to "Mandarin", "ara" to "Arab", "rus" to "Rusia"
)

fun List<String>.toLanguageText(): String =
    if (isEmpty()) "Tidak diketahui"
    else joinToString(", ") { languageNames[it] ?: it.uppercase() }