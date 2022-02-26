package com.polyfrontiere.api.source.passport;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.web.client.RestClientException;

import com.polyfrontiere.data.declaration.domain.PassDeclaration;
import com.polyfrontiere.data.passport.Passport;

public interface PassportSourceAPI {
	public Passport[] getPassportInfoForIds(PassDeclaration[] ids) throws IOException, RestClientException, URISyntaxException;
}
