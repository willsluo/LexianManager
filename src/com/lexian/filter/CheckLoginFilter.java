package com.lexian.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginFilter implements Filter {

	private String loginAction;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		loginAction = filterConfig.getServletContext().getInitParameter("login-action");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (canContinue(req, resp)) {
			chain.doFilter(request, response);
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			String jsonStr = "{\"code\":\"-101\",\"data\":\"请先登录\"}";
			PrintWriter out = null;
			try {
			    out = response.getWriter();
			    out.write(jsonStr);
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}
		}
	}

	private boolean canContinue(HttpServletRequest req, HttpServletResponse resp) {
		boolean canContinue = true;

		if (!req.getRequestURI().toString().contains(loginAction)) {
			HttpSession session = req.getSession();
			if (session.getAttribute("managerId") == null) {
				canContinue = false;
			}
		}

		return canContinue;
	}

	@Override
	public void destroy() {

	}

}
