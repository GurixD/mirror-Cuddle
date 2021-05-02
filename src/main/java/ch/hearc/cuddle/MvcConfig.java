package ch.hearc.cuddle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        

        registry.addResourceHandler("/media/**").addResourceLocations("file:///app/static/media/");
    }
}
