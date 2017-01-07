package com.mycompany;

import java.time.LocalDateTime;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import services.BezorgService;
import services.TestEmailLogicaImpl;

public class BezorgPage extends BasicPage {

	private static final long serialVersionUID = 1L;

	private String omschrijving;

	private Label omschrijvingLabel;

	private transient BezorgService service;

	public BezorgPage() {
		service = new BezorgService(new TestEmailLogicaImpl());
		
		Model<Long> codeModel = new Model<>(new Long(0));
		Form<Long> form = new Form<Long>("afhaalForm", codeModel);
		add(form);

		form.add(new TextField<Long>("code", form.getModel()));

		AjaxSubmitLink afhaalLink = new AjaxSubmitLink("afhalen", form) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				omschrijving = service.haalPakketAf((Long) form.getModelObject());
				target.add(omschrijvingLabel);
			}
		};
		add(afhaalLink.setBody(Model.of("Haal pakket af")));
		
		AjaxSubmitLink bezorgLink = new AjaxSubmitLink("bezorgen", form) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				omschrijving = service.bezorgPakket((Long) form.getModelObject(), LocalDateTime.now());
				target.add(omschrijvingLabel);
			}
		};
		add(bezorgLink.setBody(Model.of("Bezorg pakket")));

		AjaxSubmitLink afleverLink = new AjaxSubmitLink("afleveren", form) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				omschrijving = service.leverPakketAf((Long) form.getModelObject());
				target.add(omschrijvingLabel);
			}
		};
		add(afleverLink.setBody(Model.of("Lever pakket af")));
		
		omschrijvingLabel = new Label("omschrijving", new Model<String>("omschrijvingModel") {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return omschrijving;
			}
		});
		add(omschrijvingLabel.setOutputMarkupId(true));

	}
}
