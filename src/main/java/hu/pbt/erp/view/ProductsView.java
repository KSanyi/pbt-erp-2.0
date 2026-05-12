package hu.pbt.erp.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import hu.pbt.erp.MainLayout;

import java.util.List;

@PageTitle("Products")
@Route(value = "products", layout = MainLayout.class)
public class ProductsView extends VerticalLayout {

    record Product(String code, String name, String category, String unit, double price, int stock) {}

    public ProductsView() {
        setSizeFull();
        setPadding(true);

        Grid<Product> grid = new Grid<>(Product.class, false);
        grid.addColumn(Product::code).setHeader("Code").setAutoWidth(true);
        grid.addColumn(Product::name).setHeader("Name").setAutoWidth(true);
        grid.addColumn(Product::category).setHeader("Category").setAutoWidth(true);
        grid.addColumn(Product::unit).setHeader("Unit").setAutoWidth(true);
        grid.addColumn(p -> "$%.2f".formatted(p.price())).setHeader("Price").setAutoWidth(true);
        grid.addColumn(Product::stock).setHeader("Stock").setAutoWidth(true);
        grid.setItems(List.of(
            new Product("PRD-001", "Steel Beam 6m",       "Raw Materials",  "pcs",   45.00, 320),
            new Product("PRD-002", "Copper Wire 1mm",     "Raw Materials",  "m",      0.85, 5000),
            new Product("PRD-003", "Hydraulic Pump A1",   "Components",     "pcs", 1250.00,  48),
            new Product("PRD-004", "Safety Helmet",       "Safety",         "pcs",   18.50, 210),
            new Product("PRD-005", "Industrial Solvent",  "Chemicals",      "L",      3.20, 880)
        ));

        add(new H2("Products"), new Paragraph("Manage product catalogue and pricing."), grid);
        expand(grid);
    }
}
