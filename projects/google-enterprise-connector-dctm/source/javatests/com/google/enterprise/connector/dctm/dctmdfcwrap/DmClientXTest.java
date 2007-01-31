package com.google.enterprise.connector.dctm.dctmdfcwrap;

import com.documentum.fc.client.DfQuery;
import com.google.enterprise.connector.dctm.dfcwrap.IClient;
import com.google.enterprise.connector.dctm.dfcwrap.IClientX;
import com.google.enterprise.connector.dctm.dfcwrap.IId;
import com.google.enterprise.connector.dctm.dfcwrap.ILoginInfo;
import com.google.enterprise.connector.dctm.dfcwrap.IQuery;
import com.google.enterprise.connector.spi.RepositoryException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DmClientXTest extends TestCase {
	IClientX clientX;
	/*
	 * Test method for 'com.google.enterprise.connector.dctm.dctmdfcwrap.DmClientX.getId(String)'
	 */
	public void setUp() throws Exception{
		super.setUp();
		clientX = new DmClientX();
	}
	
	public void testGetId() {
		IId id = clientX.getId("xxxxxxxxxxxxxxxx");
		Assert.assertTrue(id instanceof DmId);
	}


	public void testGetLocalClient() throws RepositoryException {
		IClient localClient = clientX.getLocalClient();
		Assert.assertTrue(localClient instanceof DmClient);		
	}
	
	public void testGetQuery(){
		IQuery query=clientX.getQuery();
		Assert.assertTrue(query instanceof DmQuery);
	}
	
	public void testGetLoginInfo(){
		ILoginInfo loginInfo=clientX.getLoginInfo();
		Assert.assertTrue(loginInfo instanceof DmLoginInfo);
		loginInfo.setUser("max");
		loginInfo.setPassword("foo");
		Assert.assertEquals("max",loginInfo.getUser());
		Assert.assertEquals("foo",loginInfo.getPassword());		
	}

}