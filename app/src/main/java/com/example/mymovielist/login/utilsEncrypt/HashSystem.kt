package com.example.mymovielist.login.utilsEncrypt

import java.security.MessageDigest

    fun sha256(input: String, salt: String) = hashString("SHA-256", input, salt)

    fun hashString(type: String, input: String, salt: String): String {
        val HEX_CHARS = salt
        val bytes = MessageDigest
            .getInstance(type)
            .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }
        return result.toString()
    }