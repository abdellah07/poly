package com.polyfrontiere.data.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

/**
 * this class is used to encode and decode the string that we will stock in QrCode
 */
public class StringEncoder {
    private byte[] original;

    public StringEncoder(String original) {
        this.original = original.getBytes(StandardCharsets.UTF_8);
    }

    private byte[] encodeBase64(){
        return Base64.getEncoder().encode(original);
    }

    byte[] decodeBase64(){
        return Base64.getDecoder().decode(original);
    }

    public String encode() throws IOException {
        /*
    	original = encodeBase64();
        original = encodeBase64();
        original = compressBArray(original);
        */
        return new String(original);
    }

    public String decode() throws IOException {
    	/*
        original = decompress(original);
        original = decodeBase64();
        original = decodeBase64();
        */
        return new String(original);
    }

    byte[] compressBArray(byte [] bArray) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (DeflaterOutputStream dos = new DeflaterOutputStream(os)) {
            dos.write(bArray);
        }
        return os.toByteArray();
    }

    byte[] decompress(byte[] compressedTxt) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (OutputStream ios = new InflaterOutputStream(os)) {
            ios.write(compressedTxt);
        }

        return os.toByteArray();
    }
}
