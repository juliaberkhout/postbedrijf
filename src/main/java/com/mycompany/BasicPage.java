package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import data.KlantDummy;

public class BasicPage extends WebPage{

	private static final long serialVersionUID = 1L;

	public BasicPage() {
		Link<Void> aanmeldLink = new Link<Void>("aanmeldLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new AanmeldPage_1(new KlantDummy(), new KlantDummy()));
			}
		};
		add(aanmeldLink.setBody(Model.of("Aanmelden")));

		Link<Void> traceLink = new Link<Void>("traceLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new TrackAndTracePage());
			}
		};
		add(traceLink.setBody(Model.of("Track-and-trace")));
		
		Link<Void> homeLink = new Link<Void>("homeLink") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new HomePage());
			}
		};
		add(homeLink.setBody(Model.of("Home")));
		
		Link<Void> bezorgLink = new Link<Void>("bezorgLink") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new BezorgPage());
			}
		};
		add(bezorgLink.setBody(Model.of("Bezorgen")));
	}
}
