package com.mercury.pm.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.mercury.pm.security.SecurityUtils;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
			throws IOException, ServletException {

		SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, "Not authorized resources", exception);
	}
}
