package com.polyfrontiere.data.utils;

import java.util.ArrayList;
import java.util.List;

import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;

public class ParserUtils {	
	public static PassDeclaration[] getPassportIDsFromDeclarations(Declaration[] declarations) {
		List<PassDeclaration> passportIDs = new ArrayList<>();
		for(Declaration declaration : declarations) {
			passportIDs.add(declaration.getConducteur().getPassport());
		}
		
		return passportIDs.toArray(new PassDeclaration[passportIDs.size()]);
	}
}
