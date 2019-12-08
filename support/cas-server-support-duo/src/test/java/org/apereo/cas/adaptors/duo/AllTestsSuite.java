package org.apereo.cas.adaptors.duo;

import org.apereo.cas.adaptors.duo.web.flow.DuoSecurityMultifactorWebflowConfigurerTests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * This is {@link org.apereo.cas.AllTestsSuite}.
 *
 * @author Misagh Moayyed
 * @since 6.0.0-RC3
 */
@SelectClasses(DuoSecurityMultifactorWebflowConfigurerTests.class)
@RunWith(JUnitPlatform.class)
public class AllTestsSuite {
}
