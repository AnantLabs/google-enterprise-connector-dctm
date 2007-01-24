package com.google.enterprise.connector.dctm.dctmdfcwrap;

import com.google.enterprise.connector.dctm.dfcwrap.IClient;
import com.google.enterprise.connector.dctm.dfcwrap.IClientX;
import com.google.enterprise.connector.dctm.dfcwrap.IId;
import com.google.enterprise.connector.spi.RepositoryException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DmClientXTest extends TestCase {

	/*
	 * Test method for 'com.google.enterprise.connector.dctm.dctmdfcwrap.DmClientX.getId(String)'
	 */
	public void testGetId() {
		IClientX clientX = new DmClientX();
		IId id = clientX.getId("xxxxxxxxxxxxxxxx");
		Assert.assertTrue(id instanceof DmId);
	}

	/*
	 * Test method for 'com.google.enterprise.connector.dctm.dctmdfcwrap.DmClientX.getLocalClient()'
	 */
	public void testGetLocalClient() throws RepositoryException {
		IClientX clientX = new DmClientX();
		IClient localClient = clientX.getLocalClient();
		Assert.assertTrue(localClient instanceof DmClient);		
	}

}