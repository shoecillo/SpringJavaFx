package com.sh.springJavaFx.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sh.springJavaFx.app.engine.EngineView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author shoe011
 * Main abstract class, we have to extend to this class and implements main method.  
 *
 */
public abstract class SpringJavaFxApplication extends Application {

	protected EngineView engine;
	protected VBox rootLayout;
	protected Stage primaryStage;
	protected ConfigurableApplicationContext springContext;
	
	public SpringJavaFxApplication() {
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		buildView();
		
	}
	
	/**
	 * Build a new view
	 */
	private void buildView() {

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	@Override
	public void init() throws Exception {
		
		springContext = SpringApplication.run(this.getClass());
		final String pathRoot = springContext.getEnvironment().getProperty("views.root");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(springContext::getBean);
		loader.setLocation(this.getClass().getResource(pathRoot));		
		rootLayout = loader.load();
		
		
	}
}
