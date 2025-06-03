package com.github.lotashinski.ui;

import java.io.IOException;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.ee10.servlet.SessionHandler;
import org.eclipse.jetty.server.Server;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.github.lotashinski.ui.config.DotenvUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
	
	public static void main(String[] args) throws Exception {
		String portStr = DotenvUtils.dotenv()
				.get("SERVER_PORT", "8000");
		Integer port = Integer.valueOf(portStr);
		new App().startJetty(port);
	}
	
	private void startJetty(int port) throws Exception {
		log.info("Starting server at port {}", port);
		
		Server server = new Server(port);
		server.setHandler(getServletContextHandler(getContext()));
		server.start();
		
		log.info("Server started at port {}", port);
		server.join();
	}

	private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
		ServletContextHandler contextHandler = new ServletContextHandler();
		contextHandler.setErrorHandler(null);
		contextHandler.setContextPath("/");
		
		contextHandler.setSessionHandler(new SessionHandler());
		
		contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/");
		contextHandler.addEventListener(new ContextLoaderListener(context));
		contextHandler.addEventListener(new RequestContextListener());
		
		return contextHandler;
	}

	private static WebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.github.lotashinski.ui.config");
		
		return context;
	}
	
}
