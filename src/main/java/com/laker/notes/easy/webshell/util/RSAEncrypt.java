package com.laker.notes.easy.webshell.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
@Component
public class RSAEncrypt {

    /**
     * ���������Կ��
     *
     * @throws NoSuchAlgorithmException
     */
    private static String publicKeyString;
    private static String privateKeyString;

    public static String getPublicKeyString() {
        return publicKeyString;
    }

    public static void setPublicKeyString(String publicKeyString) {
        RSAEncrypt.publicKeyString = publicKeyString;
    }

    public static String getPrivateKeyString() {
        return privateKeyString;
    }

    public static void setPrivateKeyString(String privateKeyString) {
        RSAEncrypt.privateKeyString = privateKeyString;
    }

    public RSAEncrypt() {
        try {
            genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator���������ɹ�Կ��˽Կ�ԣ�����RSA�㷨���ɶ���
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // ��ʼ����Կ������������Կ��СΪ96-1024λ
        keyPairGen.initialize(2048, new SecureRandom());
        // ����һ����Կ�ԣ�������keyPair��
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // �õ�˽Կ
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // �õ���Կ
        publicKeyString = new String(Base64Coded.encode(publicKey.getEncoded()));
        // �õ�˽Կ�ַ���
        privateKeyString = new String(Base64Coded.encode((privateKey.getEncoded())));
        // ����Կ��˽Կ���浽Map
        //keyMap.put(0,publicKeyString);  //0��ʾ��Կ
        //keyMap.put(1,privateKeyString);  //1��ʾ˽Կ
    }

    /**
     * RSA��Կ����
     *
     * @param str       �����ַ���
     * @param publicKey ��Կ
     * @return ����
     * @throws Exception ���ܹ����е��쳣��Ϣ
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64����Ĺ�Կ
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA����
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA˽Կ����
     *
     * @param str        �����ַ���
     * @param privateKey ˽Կ
     * @return ����
     * @throws Exception ���ܹ����е��쳣��Ϣ
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64λ������ܺ���ַ���
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64�����˽Կ
        ////byte[] decoded = Base64.decodeBase64(publicKeyString);
        ////String s=encrypt("Myapp_123", publicKeyString);
        ////System.out.println(s);
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA����
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}

