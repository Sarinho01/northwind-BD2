package com.saroswork.nothwindexample.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/")
public class MainView extends VerticalLayout {

    /**
     * Creates a new MainView.
     */
    public MainView() {
        add(
                new H1("Teste do saro")
        );
        this.setAlignItems(Alignment.CENTER);


    }

}
