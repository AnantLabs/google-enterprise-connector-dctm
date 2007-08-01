package com.google.enterprise.connector.dctm;

import java.util.Iterator;

import junit.framework.TestCase;

import com.google.enterprise.connector.dctm.dctmdfcwrap.DmClientX;
import com.google.enterprise.connector.dctm.dfcwrap.IClient;
import com.google.enterprise.connector.dctm.dfcwrap.IClientX;
import com.google.enterprise.connector.dctm.dfcwrap.ILoginInfo;
import com.google.enterprise.connector.dctm.dfcwrap.ISessionManager;
import com.google.enterprise.connector.spi.Property;
import com.google.enterprise.connector.spi.RepositoryException;
import com.google.enterprise.connector.spi.SpiConstants;
import com.google.enterprise.connector.spi.Value;
import com.google.enterprise.connector.spiimpl.StringValue;

public class DctmSysobjectDocumentTest extends TestCase {

	IClientX dctmClientX = null;

	IClient localClient = null;

	ISessionManager sessionManager = null;

	public void setUp() throws Exception {
		super.setUp();
		dctmClientX = new DmClientX();

		localClient = dctmClientX.getLocalClient();

		sessionManager = localClient.newSessionManager();

		ILoginInfo loginInfo = dctmClientX.getLoginInfo();
		loginInfo.setUser(DmInitialize.DM_LOGIN_OK1);
		loginInfo.setPassword(DmInitialize.DM_PWD_OK1);
		sessionManager.setIdentity(DmInitialize.DM_DOCBASE, loginInfo);
		sessionManager.setDocbaseName(DmInitialize.DM_DOCBASE);
	}

	public void testGetPropertyNames() throws RepositoryException {

		DctmSysobjectDocument dctmSpm = new DctmSysobjectDocument(
				DmInitialize.DM_ID1, sessionManager, dctmClientX, "false",
				DmInitialize.included_meta, DmInitialize.excluded_meta);

		Iterator iterator = dctmSpm.getPropertyNames().iterator();
		int counter = 0;
		while (iterator.hasNext()) {
			iterator.next();
			counter++;
		}
		assertEquals(8, counter);
	}

	public void testFindProperty() throws RepositoryException {
		DctmSysobjectDocument dctmSpm = new DctmSysobjectDocument(
				DmInitialize.DM_ID2, sessionManager, dctmClientX, "false",
				DmInitialize.included_meta, DmInitialize.excluded_meta);
		Property property = dctmSpm.findProperty("keywords");
		assertTrue(property instanceof DctmSysobjectProperty);
		Value val = null;
		
		while ((val = property.nextValue()) != null){
			assertTrue(val instanceof StringValue);
		}
		property = dctmSpm.findProperty("r_object_id");
		assertTrue(property instanceof DctmSysobjectProperty);
	
		assertEquals(DmInitialize.DM_ID2, property.nextValue().toString());
		property = dctmSpm.findProperty(SpiConstants.PROPNAME_DOCID);
		assertTrue(property instanceof DctmSysobjectProperty);
		assertEquals(DmInitialize.DM_VSID2, property.nextValue().toString());
	}

}
