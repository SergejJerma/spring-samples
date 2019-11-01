package eshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProcessingTimeLogInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = Logger.getLogger(ProcessingTimeLogInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		long startTime = System.currentTimeMillis();
		// Request objektui nustatome atributą - startTime
		request.setAttribute("startTime", startTime);
		// Jeigu būtų FALSE, užklausa nepraeitų pro šį metodą ir būtų "suvalgyta" interceptor klasės
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		String path = request.getRequestURL() + queryString;
		// Pasiimame atributą atgal
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();

		LOGGER.info(String.format("%s millisecond taken to process the request %s.", (endTime - startTime), path));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
