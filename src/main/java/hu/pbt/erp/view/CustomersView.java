package hu.pbt.erp.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import hu.pbt.erp.MainLayout;

import java.util.List;

@PageTitle("Customers")
@Route(value = "customers", layout = MainLayout.class)
public class CustomersView extends VerticalLayout {

    record Customer(String name, String contact, String email, String phone, String city, String country) {}

    public CustomersView() {
        setSizeFull();
        setPadding(true);

        Grid<Customer> grid = new Grid<>(Customer.class, false);
        grid.addColumn(Customer::name).setHeader("Company").setAutoWidth(true);
        grid.addColumn(Customer::contact).setHeader("Contact Person").setAutoWidth(true);
        grid.addColumn(Customer::email).setHeader("Email").setAutoWidth(true);
        grid.addColumn(Customer::phone).setHeader("Phone").setAutoWidth(true);
        grid.addColumn(Customer::city).setHeader("City").setAutoWidth(true);
        grid.addColumn(Customer::country).setHeader("Country").setAutoWidth(true);
        grid.setItems(List.of(
            new Customer("Acme Corporation",  "John Smith",   "john@acme.com",     "+1-555-0100", "New York",    "USA"),
            new Customer("Globex Corp",       "Jane Doe",     "jane@globex.com",   "+1-555-0101", "Springfield", "USA"),
            new Customer("Umbrella Ltd",      "Bob Johnson",  "bob@umbrella.com",  "+44-20-7946-0958", "London",  "UK"),
            new Customer("Stark Industries",  "Pepper Potts", "pepper@stark.com",  "+1-555-0102", "Malibu",      "USA"),
            new Customer("Wayne Enterprises", "Lucius Fox",   "lucius@wayne.com",  "+1-555-0103", "Gotham",      "USA")
        ));

        add(new H2("Customers"), new Paragraph("Manage customer master data."), grid);
        expand(grid);
    }
}
