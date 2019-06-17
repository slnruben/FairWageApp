package com.urjc.rubnsnchez.fairwage.app.common;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ALGORITHM;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CHARSET_TYPE;

public class CipherUtil {
    public static KeyPair getKeyPair() {
        KeyPair kp = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
            kpg.initialize(2048);
            kp = kpg.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kp;
    }

    public static PublicKey stringToPublicKey(String publicKeyString) {
        PublicKey publicKey = null;
        try {
            byte[] byteArray = Base64.decode(publicKeyString, Base64.NO_WRAP);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(byteArray));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PublicKey byteArrayToPublicKey(byte[] publicKeyByte) {
        PublicKey publicKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey byteArrayToPrivateKey(byte[] privateKeyByte) {
        PrivateKey privateKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static String encryptBase64(PublicKey publicKey, String str) {
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] byteArray = cipher.doFinal(str.getBytes(CHARSET_TYPE));
            result = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decrypt(PrivateKey privateKey, byte[] value) {
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = new String(cipher.doFinal(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
