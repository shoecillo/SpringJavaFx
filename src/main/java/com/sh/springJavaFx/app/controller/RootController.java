package com.sh.springJavaFx.app.controller;

import org.springframework.stereotype.Controller;

import javafx.scene.layout.HBox;

/**
 * 
 * @author shoe011
 * Main root controller that have to be implemented as main layout
 *
 */
@Controller
public interface RootController {

	
	public HBox gethBoxContent();
	
}
