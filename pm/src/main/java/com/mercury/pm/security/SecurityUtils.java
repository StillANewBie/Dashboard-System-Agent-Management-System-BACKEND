package com.mercury.pm.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.pm.beans.User;
import com.mercury.pm.http.AuthenticationSuccessResponse;
import com.mercury.pm.http.Response;

public class SecurityUtils {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static void sendResponse(HttpServletResponse httpServletResponse, int status, String message,
			Exception exception) throws IOException {
		Response response = new Response(exception == null, status, message);
		flushResponse(httpServletResponse, response);

	}

	public static void sendAuthenticationSuccessResponse(HttpServletResponse httpServletResponse, int status,
			String message, Exception exception, User user) throws IOException {
		Response response = new AuthenticationSuccessResponse(exception == null, status, message, user);
		flushResponse(httpServletResponse, response);
	}

	public static void flushResponse(HttpServletResponse httpServletResponse, Response response) throws IOException {
		httpServletResponse.setContentType("application/json);charset=UTF-8");
		httpServletResponse.setStatus(response.getCode());
		PrintWriter writer = httpServletResponse.getWriter();
		writer.write(mapper.writeValueAsString(response));
		writer.flush();
		writer.close();
	}

	public static boolean isAdmin(Collection<? extends GrantedAuthority> profiles) {
		boolean isAdmin = false;
		for (GrantedAuthority profile : profiles) {
			// TODO
//			if (profle.getAuthority().equals("ROLE_ADMIN")) {
//				isAdmin = true;
//			}
		}
		return isAdmin;
	}
}
