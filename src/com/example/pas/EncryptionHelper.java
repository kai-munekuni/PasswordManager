package com.example.pas;

import android.content.Context;
import android.util.Base64;

import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.openssl.PEMReader;
import org.spongycastle.openssl.PasswordFinder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyPair;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import javax.crypto.Cipher;

public final class EncryptionHelper {

    public static String encrypt(String text, int pubKeyResId, String password) {
        Security.addProvider(new BouncyCastleProvider());
        Context context = App.getContext();
        InputStreamReader publicKeyReader = new InputStreamReader(context.getResources().openRawResource(pubKeyResId));
        Key publicKey = null;
        try {
             publicKey = readPublicKey(publicKeyReader, password);
        } catch (IOException e) { }
        byte[] encrypted = encryptFromKey(text, publicKey);
        String encryptedStr = "";
        try {
            encryptedStr = new String(Base64.encode(encrypted, Base64.DEFAULT), "UTF8");
        } catch (UnsupportedEncodingException e) { }
        return encryptedStr;
    }

    private static byte[] encryptFromKey(String text, Key pubkey) {
        try {
            Cipher rsa;
            rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE, pubkey);
            return rsa.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted, int privateResId, String password) {
        Security.addProvider(new BouncyCastleProvider());
        Context context = App.getContext();
        InputStreamReader privateKeyReader = new InputStreamReader(context.getResources().openRawResource(privateResId));
        KeyPair keyPair = null;
        try {
            keyPair = readKeyPair(privateKeyReader, password);
        } catch (IOException e) { }
        return decryptFromKey(Base64.decode(encrypted, Base64.DEFAULT), keyPair.getPrivate());
    }

    private static String decryptFromKey(byte[] buffer, Key decryptionKey) {
        try {
            Cipher rsa;
            rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.DECRYPT_MODE, decryptionKey);
            byte[] utf8 = rsa.doFinal(buffer);
            return new String(utf8, "UTF8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static KeyPair readKeyPair(InputStreamReader reader, String keyPassword) throws IOException {
        PEMReader r = new PEMReader(reader, new DefaultPasswordFinder(keyPassword.toCharArray()));
        try {
            return (KeyPair) r.readObject();
        } catch (IOException ex) {
            throw ex;
        } finally {
            r.close();
            reader.close();
        }

    }

    private static Key readPublicKey(InputStreamReader reader, String keyPassword) throws IOException {
        PEMReader r = new PEMReader(reader, new DefaultPasswordFinder(keyPassword.toCharArray()));
        try {
            return (RSAPublicKey) r.readObject();
        } catch (IOException ex) {
            throw ex;
        } finally {
            r.close();
            reader.close();
        }
    }

    private static class DefaultPasswordFinder implements PasswordFinder {

        private final char [] password;

        private DefaultPasswordFinder(char[] password) {
            this.password = password;
        }

        @Override
        public char[] getPassword() {
            return Arrays.copyOf(password, password.length);
        }
    }
}