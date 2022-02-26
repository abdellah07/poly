package com.polyfrontiere.data.fingerprint;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class represent a fingerprint using an image of it
 */
public class Fingerprint {
    FingerprintTemplate fingerprintTemplate;

    /**
     *
     * @param imageFilePath the path to the image that represent the fingerprint
     * @throws IOException in case the file is not found
     */
    public Fingerprint(String imageFilePath) throws IOException {
        FingerprintImage fingerprintImage =new FingerprintImage(
                Files.readAllBytes(Paths.get(imageFilePath)),
                new FingerprintImageOptions()
                        .dpi(2000));
        fingerprintTemplate = new FingerprintTemplate(fingerprintImage);
    }

    public Fingerprint(byte[] fingerprintSerial) throws IOException {
        fingerprintTemplate = new FingerprintTemplate(fingerprintSerial);
    }

    public FingerprintTemplate getFingerprintTemplate() {
        return fingerprintTemplate;
    }

    public byte[] serialize(){
        return fingerprintTemplate.toByteArray();
    }
}
