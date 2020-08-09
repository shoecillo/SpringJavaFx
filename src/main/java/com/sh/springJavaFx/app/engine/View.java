package com.sh.springJavaFx.app.engine;

public class View {
	
	private String name;
	
	private String path;
	
	

	/**
	 * @param name
	 * @param path
	 */
	public View(String name, String path) {
		super();
		this.name = name;
		this.path = path;
	}
	
	

	/**
	 * 
	 */
	public View() {
		super();
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
