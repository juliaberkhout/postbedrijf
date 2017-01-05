package com.mycompany;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class AanmeldenSuccessvolPage extends WebPage {
	
	private static final long serialVersionUID = 1L;

	public AanmeldenSuccessvolPage(long code) {
		add(new Label("code", String.valueOf(code)));
	}
}
