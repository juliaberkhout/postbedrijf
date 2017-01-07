package com.mycompany;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import data.KlantDummy;
import services.KlantService;
import services.TestEmailLogicaImpl;

public class AanmeldPage_2 extends BasicPage {
	private static final long serialVersionUID = 1L;
	private TextField<Integer> gewichtField;
	private Actie actie = Actie.OPSLAAN;

	public AanmeldPage_2(KlantDummy afzender, KlantDummy ontvanger) {

		IModel<KlantDummy> klantModel = new CompoundPropertyModel<KlantDummy>(ontvanger);

		Form<KlantDummy> form = new Form<KlantDummy>("aanmeldForm", klantModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				if (actie == Actie.OPSLAAN) {
					KlantService klantService = new KlantService(new TestEmailLogicaImpl());
					long code = klantService.aanmelden(afzender, getModelObject(), gewichtField.getModelObject());
					setResponsePage(new AanmeldenSuccessvolPage(code));
				} else if (actie == Actie.VORIGE) {
					setResponsePage(new AanmeldPage_1(afzender, getModelObject()));
				}
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
		SubmitLink opslaanLink = new SubmitLink("opslaan", form) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				actie = Actie.OPSLAAN;
			}
		};
		add(opslaanLink.setBody(Model.of("Opslaan")));
		SubmitLink vorigeLink = new SubmitLink("vorige", form) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				actie = Actie.VORIGE;
			}
		};
		add(vorigeLink.setBody(Model.of("Vorige")));
	}

	private enum Actie {
		VORIGE, OPSLAAN;
	}

}
