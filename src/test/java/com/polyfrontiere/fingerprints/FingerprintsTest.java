package com.polyfrontiere.fingerprints;

import com.polyfrontiere.data.fingerprint.FingerPrintMatcher;
import com.polyfrontiere.data.fingerprint.Fingerprint;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FingerprintsTest {
    @Test
    public void fingerPrintMatcher() throws IOException {
        Fingerprint fingerprint = new Fingerprint("src/main/resources/fingerprints/print1.jpg");

        Fingerprint secondFingerprint = new Fingerprint("src/main/resources/fingerprints/print2.jpg");

        FingerPrintMatcher fingerPrintMatcher = new FingerPrintMatcher(fingerprint);

        assertTrue(fingerPrintMatcher.match(fingerprint));

        assertFalse(fingerPrintMatcher.match(secondFingerprint));

    }
}
