package com.sh.springJavaFx.app;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sh.springJavaFx.app.engine.View;


/**
 * 
 * @author shoe011
 * Configuration class for register all fxml files in views.root property.	
 *
 */
@Configuration
public class SpringJavaFxConfig {
	
	@Value("${views.root}")
	private String rootPath;
	
	/**
	 * Register all fxml files in a Map, key is  name, value is path
	 * @return Map <String,String>
	 */
	@Bean("mapViews")
	public Map<String,String> fxmlViews(){
		
		Map<String,String> ls = null;
		URI uri;
		Path path;
		try {
			String ruta = this.getClass().getResource(rootPath).toURI().toString();
			if(ruta.contains("!")){
				String[] parts = ruta.split("!");
				Map<String, String> env = new HashMap<>();
				FileSystem fs = FileSystems.newFileSystem(URI.create(parts[0]),env);
				path = fs.getPath(parts[1],parts[2]);
				uri = URI.create(path.toString());
				path = path.getParent();
				
				
			}else {
				uri = this.getClass().getResource(rootPath).toURI();
				path = new File(uri).getParentFile().toPath();
			}
			
			Path y = path;
			ls = Files
			.walk(y).filter(it -> it.toString().endsWith(".fxml"))
			.map(it  -> new View(it.getFileName().toString().split("\\.")[0] , "/"+y.getFileName().toString().concat("/"+it.getFileName().toString())))
			.collect(Collectors.toMap(View::getName, View::getPath));
		
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return ls;
	}
	
	
}
