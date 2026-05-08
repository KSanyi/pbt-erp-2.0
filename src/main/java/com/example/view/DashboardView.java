package com.example.view;

import com.example.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Dashboard")
@Route(value = "", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    public DashboardView() {
        setPadding(true);
        setSpacing(true);

        add(
            new H2("Dashboard"),
            new Paragraph("Welcome to the ERP System — your business at a glance."),
            buildKpiRow()
        );
    }

    private Component buildKpiRow() {
        return new HorizontalLayout(
            kpiCard("Customers",        "247",   VaadinIcon.USERS),
            kpiCard("Open Orders",      "38",    VaadinIcon.MONEY),
            kpiCard("Products",         "1 432", VaadinIcon.GRID),
            kpiCard("Pending Invoices", "12",    VaadinIcon.RECORDS)
        );
    }

    private static Component kpiCard(String label, String value, VaadinIcon icon) {
        VerticalLayout card = new VerticalLayout();
        card.setWidth("180px");
        card.setPadding(true);
        card.setSpacing(false);
        card.getStyle()
            .set("border", "1px solid var(--lumo-contrast-20pct)")
            .set("border-radius", "var(--lumo-border-radius-m)");

        Span ico = new Span(icon.create());
        ico.getStyle().set("color", "var(--lumo-primary-color)");

        Span val = new Span(value);
        val.getStyle()
           .set("font-size", "var(--lumo-font-size-xxl)")
           .set("font-weight", "bold");

        Span lbl = new Span(label);
        lbl.getStyle().set("color", "var(--lumo-secondary-text-color)");

        card.add(ico, val, lbl);
        return card;
    }
}
