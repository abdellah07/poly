package com.polyfrontiere.data.fingerprint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Objects;

public class UserDetail {
    private String passportId;
    private byte[] fingerprint;

    @JsonCreator
    public UserDetail(@JsonProperty("passportId") String passportId,@JsonProperty("fingerprint") byte[] fingerprint) {
        this.passportId = passportId;
        this.fingerprint = fingerprint;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(byte[] fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String parseIntoJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetail that = (UserDetail) o;
        return Objects.equals(passportId, that.passportId) && Arrays.equals(fingerprint, that.fingerprint);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(passportId);
        result = 31 * result + Arrays.hashCode(fingerprint);
        return result;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "passportId='" + passportId + '\'' +
                ", fingerprint=" + Arrays.toString(fingerprint) +
                '}';
    }
}
