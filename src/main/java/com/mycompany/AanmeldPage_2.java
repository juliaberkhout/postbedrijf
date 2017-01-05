package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import data.Klant;
import data.KlantDummy;
import services.KlantService;
import services.TestEmailLogicaImpl;

public class AanmeldPage_2 extends WebPage {
	private static final long serialVersionUID = 1L;
	private TextField<Integer> gewichtField;

	public AanmeldPage_2(KlantDummy afzender) {

		IModel<KlantDummy> klantModel = new CompoundPropertyModel<KlantDummy>(new KlantDummy());

		Form<KlantDummy> form = new Form<KlantDummy>("aanmeldForm", klantModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				KlantService klantService = new KlantService(new TestEmailLogicaImpl());
				long code = klantService.aanmelden(afzender, getModelObject(), gewichtField.getModelObject());
				setResponsePage(new AanmeldenSuccessvolPage(code));
			}
		};
		add(form);
		form.add(new TextField<>("naam"));
		form.add(new TextField<>("straat"));
		form.add(new TextField<>("huisnummer"));
		form.add(new TextField<>("postcode"));
		form.add(new TextField<>("plaats"));
		form.add(new TextField<>("emailadres"));

		gewichtField = new TextField<>("gewicht", Model.of(new Integer(0)), Integer.class);
		form.add(gewichtField);
		add(new SubmitLink("opslaan", form).setBody(Model.of("Opslaan")));
	}

}
