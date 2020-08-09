package com.sh.springJavaFx.app.engine;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.sh.springJavaFx.app.controller.RootController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * 
 * @author shoe011
 * Class for manage the JavaFx views
 */
@Component
public class EngineView {

	private VBox root;
	
	@Qualifier("mapViews")
	@Autowired
	private Map<String, String> views;
	
	@Autowired
	private RootController rootCtrl;
	
	private ConfigurableApplicationContext springContext;

	public VBox getRoot() {
		return root;
	}
		
	public void setRoot(VBox root) {
		this.root = root;
	}
	
	public RootController getRootCtrl() {
		return rootCtrl;
	}
	
	public ConfigurableApplicationContext getSpringContext() {
		return springContext;
	}

	public void setSpringContext(ConfigurableApplicationContext springContext) {
		this.springContext = springContext;
	}

	/**
	 * Show new JavaFx Layout
	 * @param sceneName - name of scene have to match to fxml file
	 */
	public void showLayout(String sceneName) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setControllerFactory(springContext::getBean);
			loader.setLocation(EngineView.class.getResource(views.get(sceneName)));
			Parent view = loader.load();
			HBox content =rootCtrl.gethBoxContent();
						
			if(content.getChildren().size()>=1) {
				HBox.setHgrow(view, Priority.ALWAYS);
				if(content.getChildren().size()>1) {
					content.getChildren().remove(1);
				}
				
				
			}
			
			content.getChildren().add(view);


		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public <T> ComponentData<T> getComponent(String sceneName) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(springContext::getBean);
		loader.setLocation(EngineView.class.getResource(views.get(sceneName)));
		ComponentData<T> data = new ComponentData<T>();
		Parent node = loader.load();
		data.setLoader(loader);
		data.setNode(node);
		data.setController(loader.getController());
		return data;
		
	}
	
	public Parent getNode(String sceneName) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(springContext::getBean);
		loader.setLocation(EngineView.class.getResource(views.get(sceneName)));
		return loader.load();
	}
	
	public FXMLLoader getLoader(String sceneName) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(springContext::getBean);
		loader.setLocation(EngineView.class.getResource(views.get(sceneName)));
		return loader;
	}
	
	public <T> T getController(String sceneName) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(springContext::getBean);
		loader.setLocation(EngineView.class.getResource(views.get(sceneName)));
		return loader.getController();
	}

	
}
