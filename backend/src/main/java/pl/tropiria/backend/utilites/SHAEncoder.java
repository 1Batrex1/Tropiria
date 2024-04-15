package pl.tropiria.backend.utilites;

import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.*;
import static pl.tropiria.backend.config.constants.SHAConstant.SHA3_512;

public class SHAEncoder {

    private static MessageDigest messageDigest;
    private static byte[] hash;
    private static StringBuilder hexString;

    private SHAEncoder() {
        throw new IllegalFormatCodePointException(UTILITY_CLASS.getCode());
    }

    public static String encode(MultipartFile photo) {

        setMessageDigest();
        setHash(photo);
        setHexString();
        return hexString.toString();
    }

    private static void setMessageDigest(){
        try {
            messageDigest = MessageDigest.getInstance(SHA3_512.getAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalFormatCodePointException(NO_ALGORITHM.getCode());
        }
    }

    private static void setHash(MultipartFile photo) {
        try {
            hash = messageDigest.digest(photo.getOriginalFilename().getBytes());
        }
        catch (NullPointerException e){
            throw new IllegalFormatCodePointException(CORRUPTED_PHOTO_FILE.getCode());
        }
    }
    private static void setHexString() {
        hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        hexString.append(SHA3_512.getExtension());

    }

}
