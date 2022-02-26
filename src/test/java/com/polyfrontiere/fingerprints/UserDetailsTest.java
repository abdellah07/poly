package com.polyfrontiere.fingerprints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polyfrontiere.data.fingerprint.UserDetail;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDetailsTest {

    @Test
    public void jsonTest() throws JsonProcessingException {
        UserDetail userDetail = new UserDetail("AT203010", new byte[]{10, 20, 30, 50, 60, 80, 20, 127, 0, 120, 127, 5});

        assertEquals("{\"passportId\":\"AT203010\",\"fingerprint\":\"ChQeMjxQFH8AeH8F\"}", userDetail.parseIntoJson());

        ObjectMapper objectMapper = new ObjectMapper();
        UserDetail userDetail2 = objectMapper.readerFor(UserDetail.class).readValue(userDetail.parseIntoJson());

        assertEquals(userDetail,userDetail2);
    }
}
