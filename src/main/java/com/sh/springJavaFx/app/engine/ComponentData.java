package com.sh.springJavaFx.app.engine;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ComponentData<T> {
	
	private Parent node;
	
	private FXMLLoader loader;
	
	private T controller;

	public Parent getNode() {
		return node;
	}

	public void setNode(Parent node) {
		this.node = node;
	}

	public FXMLLoader getLoader() {
		return loader;
	}

	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
	}

	public T getController() {
		return controller;
	}

	public void setController(T controller) {
		this.controller = controller;
	}
	
	
	
}
