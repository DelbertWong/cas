package org.apereo.cas.support.saml.web.idp.metadata;

import org.apereo.cas.services.RegisteredServiceTestUtils;
import org.apereo.cas.support.saml.BaseSamlIdPConfigurationTests;

import lombok.val;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is {@link SamlIdPMetadataControllerTests}.
 *
 * @author Misagh Moayyed
 * @since 6.2.0
 */
@Tag("SAMLMetadata")
class SamlIdPMetadataControllerTests extends BaseSamlIdPConfigurationTests {

    @Autowired
    @Qualifier("samlIdPMetadataController")
    private SamlIdPMetadataController samlIdPMetadataController;

    @Test
    public void verifyOperationByServiceId() {
        val response = new MockHttpServletResponse();
        val service = RegisteredServiceTestUtils.getService().getId();
        assertDoesNotThrow(() -> samlIdPMetadataController.generateMetadataForIdp(service, response));
    }

    @Test
    public void verifyOperation() {
        val response = new MockHttpServletResponse();
        assertDoesNotThrow(() -> samlIdPMetadataController.generateMetadataForIdp("1000", response));
    }

    @Test
    public void verifyNoServiceOperation() {
        val response = new MockHttpServletResponse();
        assertDoesNotThrow(() -> samlIdPMetadataController.generateMetadataForIdp(null, response));
    }
}
