package com.polyfrontiere.api.source.declarations;

import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.declaration.domain.PassDeclaration;

public interface DeclarationsSourceAPI {
	public Declaration[] getDeclarationsForNextHours(int nbHours) throws Exception;
	
	public PassDeclaration[] getPassportIdsForNextHours(int nbHours);
}
