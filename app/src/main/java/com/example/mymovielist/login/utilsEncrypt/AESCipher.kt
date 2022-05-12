package com.example.mymovielist.login.utilsEncrypt

import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESCipher(private val key: String) {

    private fun createCipher(mode: Int, ivBytes: ByteArray): Cipher {
        val c = Cipher.getInstance("AES/CBC/PKCS7Padding")
        val sk = SecretKeySpec(key.getSha256(), "AES")
        val iv = IvParameterSpec(ivBytes)
        c.init(Cipher.DECRYPT_MODE, sk, iv)
        return c
    }

    fun decrypt(data: String): ByteArray {
        val bytes = Base64.decode(data, Base64.DEFAULT)
        val ivBytes = bytes.take(16).toByteArray()
        val rawDataBytes = bytes.drop(16).toByteArray()
        val cipher = createCipher(Cipher.DECRYPT_MODE, ivBytes)
        return cipher.doFinal(rawDataBytes)
    }

    private fun String.getSha256(): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256").also { it.reset() }
        return digest.digest(this.toByteArray())
    }
}