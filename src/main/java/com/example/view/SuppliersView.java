package com.example.view;

import com.example.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Suppliers")
@Route(value = "suppliers", layout = MainLayout.class)
public class SuppliersView extends VerticalLayout {

    record Supplier(String name, String contact, String email, String paymentTerms, String city, String country) {}

    public SuppliersView() {
        setSizeFull();
        setPadding(true);

        Grid<Supplier> grid = new Grid<>(Supplier.class, false);
        grid.addColumn(Supplier::name).setHeader("Company").setAutoWidth(true);
        grid.addColumn(Supplier::contact).setHeader("Contact Person").setAutoWidth(true);
        grid.addColumn(Supplier::email).setHeader("Email").setAutoWidth(true);
        grid.addColumn(Supplier::paymentTerms).setHeader("Payment Terms").setAutoWidth(true);
        grid.addColumn(Supplier::city).setHeader("City").setAutoWidth(true);
        grid.addColumn(Supplier::country).setHeader("Country").setAutoWidth(true);
        grid.setItems(List.of(
            new Supplier("Steel Works Inc",    "Mike Turner",  "mike@steelworks.com",  "Net 30", "Pittsburgh",  "USA"),
            new Supplier("Euro Parts GmbH",    "Hans Müller",  "hans@europarts.de",    "Net 45", "Munich",      "Germany"),
            new Supplier("Pacific Supply Co",  "Yuki Tanaka",  "yuki@pacsupply.jp",    "Net 30", "Osaka",       "Japan"),
            new Supplier("Nordic Logistics",   "Lars Erikson", "lars@nordic.se",       "Net 60", "Stockholm",   "Sweden"),
            new Supplier("Global Materials",   "Ana Souza",    "ana@globalmtl.br",     "Net 30", "São Paulo",   "Brazil")
        ));

        add(new H2("Suppliers"), new Paragraph("Manage supplier master data."), grid);
        expand(grid);
    }
}
