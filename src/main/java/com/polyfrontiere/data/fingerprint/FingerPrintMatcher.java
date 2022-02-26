package com.polyfrontiere.data.fingerprint;

import com.machinezoo.sourceafis.FingerprintMatcher;

import java.util.List;

public class FingerPrintMatcher {
    private Fingerprint original;
    FingerprintMatcher matcher;

    public FingerPrintMatcher(Fingerprint original) {
        this.original = original;
        matcher = new FingerprintMatcher(original.getFingerprintTemplate());
    }

    private double score(Fingerprint candidate){
        return matcher.match(candidate.getFingerprintTemplate());
    }

    /**
     * this methode look for a match fingerprint in a pool of fingerprints
     * @param candidates a pool of fingerprints where this methode will look for a match
     * @return the matched fingerprint
     */
    public Fingerprint findBestMatch(List<Fingerprint> candidates){
        double higherScore = 0;
        Fingerprint bestMatch = null;
        for (Fingerprint fingerprint:
             candidates) {
            double score = score(fingerprint);
            if(score > higherScore){
                higherScore = score;
                bestMatch = fingerprint;
            }
        }
        double threshold = 40;
        return (higherScore >= threshold) ? bestMatch : null ;
    }

    /**
     * this methode check if there is a match witch the fingerprint in param
     * @param candidate
     * @return
     */
    public boolean match(Fingerprint candidate){
        double score = score(candidate);
        double threshold = 40;
        return score>=threshold;
    }
}
