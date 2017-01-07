package com.mycompany;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import services.BezorgService;
import services.TestEmailLogicaImpl;

public class TrackAndTracePage extends BasicPage {

	private static final long serialVersionUID = 1L;
	private TextField<Long> codeField;
	private String omschrijving;
	private Label omschrijvingLabel;

	public TrackAndTracePage() {

		Model<Long> codeModel = new Model<>(new Long(0));
		
		Form<Long> form = new Form<Long>("trackForm", codeModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				BezorgService service = new BezorgService(new TestEmailLogicaImpl());
				omschrijving = service.trackPakket(getModelObject());
			}
		};
		add(form);

		Model<String> omschrijvingModel = new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return omschrijving;
			}
		};
		codeField = new TextField<>("code", form.getModel(),Long.class);
		form.add(codeField);
		
		omschrijvingLabel = new Label("omschrijving", omschrijvingModel);
		add(omschrijvingLabel.setOutputMarkupId(true));

		AjaxSubmitLink opslaanLink = new AjaxSubmitLink("opslaan", form) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				BezorgService service = new BezorgService(new TestEmailLogicaImpl());
				omschrijving = service.trackPakket((Long) form.getModelObject());
				target.add(omschrijvingLabel);
			}
		};
		add(opslaanLink);
	}
}
