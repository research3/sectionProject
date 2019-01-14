package com.accp.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.accp.util.file.Reader;
import com.accp.util.file.Upload;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(Reader.VISIT_FOLDER + "**").addResourceLocations("file:" + Upload.UPLOADED_FOLDER);
	}
}