package org.apereo.cas.validation;

import org.apereo.cas.authentication.Authentication;
import org.apereo.cas.authentication.CoreAuthenticationTestUtils;
import org.apereo.cas.services.RegisteredServiceTestUtils;

import lombok.val;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link ImmutableAssertion} class.
 *
 * @author Scott Battaglia
 * @since 3.0.0
 */
@Tag("CAS")
class ImmutableAssertionTests {

    @Test
    public void verifyGettersForChainedPrincipals() {
        val list = new ArrayList<Authentication>();

        list.add(CoreAuthenticationTestUtils.getAuthentication("test"));
        list.add(CoreAuthenticationTestUtils.getAuthentication("test1"));
        list.add(CoreAuthenticationTestUtils.getAuthentication("test2"));
        val assertion = new ImmutableAssertion(CoreAuthenticationTestUtils.getAuthentication(),
            CoreAuthenticationTestUtils.getAuthentication(), list, true,
            RegisteredServiceTestUtils.getService(), RegisteredServiceTestUtils.getRegisteredService(), Map.of());
        assertEquals(list.toArray(Authentication[]::new).length, assertion.getChainedAuthentications().size());
    }

    @Test
    public void verifyGetterFalseForNewLogin() {
        val list = new ArrayList<Authentication>();
        list.add(CoreAuthenticationTestUtils.getAuthentication());
        val assertion = new ImmutableAssertion(CoreAuthenticationTestUtils.getAuthentication(),
            CoreAuthenticationTestUtils.getAuthentication(), list, false,
            RegisteredServiceTestUtils.getService(), RegisteredServiceTestUtils.getRegisteredService(), Map.of());
        assertFalse(assertion.isFromNewLogin());
    }

    @Test
    public void verifyGetterTrueForNewLogin() {
        val list = new ArrayList<Authentication>();

        list.add(CoreAuthenticationTestUtils.getAuthentication());

        val assertion = new ImmutableAssertion(
            CoreAuthenticationTestUtils.getAuthentication(),
            CoreAuthenticationTestUtils.getAuthentication(), list, true,
            RegisteredServiceTestUtils.getService(), RegisteredServiceTestUtils.getRegisteredService(), Map.of());

        assertTrue(assertion.isFromNewLogin());
    }

    @Test
    public void verifyEqualsWithNull() {
        val list = new ArrayList<Authentication>();
        list.add(CoreAuthenticationTestUtils.getAuthentication());

        val assertion = new ImmutableAssertion(
            CoreAuthenticationTestUtils.getAuthentication(),
            CoreAuthenticationTestUtils.getAuthentication(), list, true,
            RegisteredServiceTestUtils.getService(), RegisteredServiceTestUtils.getRegisteredService(), Map.of());

        assertNotEquals(null, assertion);
    }

    @Test
    public void verifyEqualsWithInvalidObject() {
        val list = new ArrayList<Authentication>();
        list.add(CoreAuthenticationTestUtils.getAuthentication());
        val assertion = new ImmutableAssertion(CoreAuthenticationTestUtils.getAuthentication(),
            CoreAuthenticationTestUtils.getAuthentication(), list, true,
            RegisteredServiceTestUtils.getService(), RegisteredServiceTestUtils.getRegisteredService(), Map.of());
        assertNotEquals("test", assertion);
    }

    @Test
    public void verifyEqualsWithValidObject() {
        val list1 = new ArrayList<Authentication>();
        val list2 = new ArrayList<Authentication>();

        val auth = CoreAuthenticationTestUtils.getAuthentication();
        list1.add(auth);
        list2.add(auth);

        val registeredService = RegisteredServiceTestUtils.getRegisteredService();
        val assertion1 = new ImmutableAssertion(auth,
            auth, list1, true,
            RegisteredServiceTestUtils.getService(), registeredService, Map.of());
        val assertion2 = new ImmutableAssertion(auth,
            auth, list2, true,
            RegisteredServiceTestUtils.getService(), registeredService, Map.of());
        assertEquals(assertion2, assertion1);
    }

    @Test
    public void verifyGetService() {
        val service = RegisteredServiceTestUtils.getService();
        val list = new ArrayList<Authentication>();
        list.add(CoreAuthenticationTestUtils.getAuthentication());
        val assertion = new ImmutableAssertion(CoreAuthenticationTestUtils.getAuthentication(),
            CoreAuthenticationTestUtils.getAuthentication(), list, false,
            service, RegisteredServiceTestUtils.getRegisteredService(), Map.of());
        assertEquals(service, assertion.getService());
    }
}
