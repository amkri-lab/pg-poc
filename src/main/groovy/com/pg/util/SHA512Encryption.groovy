package com.pg.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SHA512Encryption {
    public static String encryptTextInSHA512(String requestString)
    {
        String hash = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            md.update(salt.getBytes());
            byte[] bytes = md.digest(requestString.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return hash;
    }


}
