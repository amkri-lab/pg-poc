package com.pg.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static String hashCal(String str){
        String type="SHA-512";
        byte[] hashseq=str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try{
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (int i=0;i<messageDigest.length;i++) {
                String hex=Integer.toHexString(0xFF & messageDigest[i]);
                if(hex.length()==1) hexString.append("0");
                hexString.append(hex);
            }

        }catch(NoSuchAlgorithmException nsae){ }
        return hexString.toString();
    }
}
