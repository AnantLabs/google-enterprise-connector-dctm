package com.google.enterprise.connector.dctm.dctmdfcwrap;

import com.documentum.fc.client.IDfACL;
import com.google.enterprise.connector.dctm.dfcwrap.IACL;

public class IDctmACL implements IACL{
	static int DF_PERMIT_BROWSE; 
	
	public IDctmACL(){
		this.DF_PERMIT_BROWSE=IDfACL.DF_PERMIT_BROWSE;
	}
}
