package com.google.enterprise.connector.dctm.dctmdfcwrap;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.documentum.fc.common.DfException;
import com.google.enterprise.connector.dctm.DmInitialize;
import com.google.enterprise.connector.dctm.dfcwrap.IClient;
import com.google.enterprise.connector.dctm.dfcwrap.IClientX;
import com.google.enterprise.connector.dctm.dfcwrap.IId;
import com.google.enterprise.connector.dctm.dfcwrap.ILoginInfo;
import com.google.enterprise.connector.dctm.dfcwrap.ISession;
import com.google.enterprise.connector.dctm.dfcwrap.ISessionManager;
import com.google.enterprise.connector.dctm.dfcwrap.ISysObject;
import com.google.enterprise.connector.spi.RepositoryException;

public class DmSessionTest extends TestCase {

	IClientX dctmClientX;

	IClient localClient;

	ISessionManager sessionManager;

	ISession session;

	ILoginInfo loginInfo;

	String user = DmInitialize.DM_LOGIN_OK1;

	String password = DmInitialize.DM_PWD_OK1;

	String simpleUser = DmInitialize.DM_LOGIN_OK5;

	String passwordSimpleUser = DmInitialize.DM_PWD_OK5;

	String docbase = DmInitialize.DM_DOCBASE;

	public void setUp() throws Exception {
		super.setUp();
		dctmClientX = new DmClientX();
		localClient = dctmClientX.getLocalClient();
		sessionManager = localClient.newSessionManager();
		loginInfo = dctmClientX.getLoginInfo();
		loginInfo.setUser(user);
		loginInfo.setPassword(password);
		sessionManager.setIdentity(docbase, loginInfo);
		session = sessionManager.getSession(docbase);
	}

	public void testGetObject() throws RepositoryException, DfException {
		try {

			IId id = dctmClientX.getId(DmInitialize.DM_ID1);
			ISysObject object = session.getObject(id);

			Assert.assertNotNull(object);
			Assert.assertTrue(object instanceof DmSysObject);
		} finally {
			if (session != null) {
				sessionManager.release(session);
			}
		}
	}

	public void testGetLoginTicketForUser() throws RepositoryException {
		try {

			String ticket = session.getLoginTicketForUser(simpleUser);

			ISessionManager sessionManagerUser = dctmClientX.getLocalClient()
					.newSessionManager();
			loginInfo.setUser(simpleUser);
			loginInfo.setPassword(passwordSimpleUser);
			sessionManagerUser.setIdentity(docbase, loginInfo);

			Assert.assertNotNull(ticket);

			ILoginInfo loginUser = sessionManagerUser.getIdentity(docbase);

			Assert.assertEquals(loginUser.getUser(), simpleUser);

			Assert.assertEquals(loginUser.getPassword(), passwordSimpleUser);
		} finally {
			if (session != null) {
				sessionManager.release(session);
			}
		}
	}

}