package com.Alex.Security;

import org.springframework.security.web.context.*;

public class MessageSecurityWebApplicationInitializer
      extends AbstractSecurityWebApplicationInitializer {

	public MessageSecurityWebApplicationInitializer() {
		super(SecurityConfiguration.class);
	}
}