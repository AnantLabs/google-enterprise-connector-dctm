// Copyright 2006 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.enterprise.connector.dctm.dctmmockwrap;

import com.google.enterprise.connector.dctm.dfcwrap.IClientX;
import com.google.enterprise.connector.dctm.dfcwrap.ILoginInfo;
import com.google.enterprise.connector.dctm.dfcwrap.IQuery;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MockDmClientXTest extends TestCase {
  private IClientX dctmClientX;

  protected void setUp() {
    dctmClientX = new MockDmClientX();
  }

  public void testGetLoginInfo() {
    ILoginInfo loginInfo = dctmClientX.getLoginInfo();

    Assert.assertTrue(loginInfo instanceof MockDmLoginInfo);

    loginInfo.setUser("max");
    loginInfo.setPassword("foo");

    Assert.assertEquals("max", loginInfo.getUser());
    Assert.assertEquals("foo", loginInfo.getPassword());
  }

  public void testGetQuery() {
    IQuery query = dctmClientX.getQuery();
    assertNotNull(query);
    Assert.assertTrue(query instanceof MockDmQuery);
  }
}
