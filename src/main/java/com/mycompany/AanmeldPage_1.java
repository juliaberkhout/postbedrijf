package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import data.KlantDummy;

public class AanmeldPage_1 extends BasicPage {
	private static final long serialVersionUID = 1L;

	public AanmeldPage_1(KlantDummy afzender, KlantDummy ontvanger) {

		IModel<KlantDummy> klantModel = new CompoundPropertyModel<KlantDummy>(afzender);

		Form<KlantDummy> form = new Form<KlantDummy>("aanmeldForm", klantModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				setResponsePage(new AanmeldPage_2(getModelObject(), afzender == null? new KlantDummy() : ontvanger));
			}
		};
		add(form);
		form.add(new TextField<>("naam"));
		form.add(new TextField<>("straat"));
		form.add(new TextField<>("huisnummer"));
		form.add(new TextField<>("postcode"));
		form.add(new TextField<>("plaats"));
		form.add(new TextField<>("emailadres"));

		SubmitLink opslaanLink = new SubmitLink("volgende", form);

		add(opslaanLink);
		opslaanLink.setBody(Model.of("Volgende"));
	}

}
