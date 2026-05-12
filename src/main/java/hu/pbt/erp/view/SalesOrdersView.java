package hu.pbt.erp.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import hu.pbt.erp.MainLayout;

import java.util.List;

@PageTitle("Sales Orders")
@Route(value = "sales-orders", layout = MainLayout.class)
public class SalesOrdersView extends VerticalLayout {

    record SalesOrder(String orderNo, String customer, String date, String total, String status) {}

    public SalesOrdersView() {
        setSizeFull();
        setPadding(true);

        Grid<SalesOrder> grid = new Grid<>(SalesOrder.class, false);
        grid.addColumn(SalesOrder::orderNo).setHeader("Order No.").setAutoWidth(true);
        grid.addColumn(SalesOrder::customer).setHeader("Customer").setAutoWidth(true);
        grid.addColumn(SalesOrder::date).setHeader("Date").setAutoWidth(true);
        grid.addColumn(SalesOrder::total).setHeader("Total").setAutoWidth(true);
        grid.addColumn(SalesOrder::status).setHeader("Status").setAutoWidth(true);
        grid.setItems(List.of(
            new SalesOrder("SO-2025-0042", "Acme Corporation",  "2025-04-28", "$12 400.00", "Confirmed"),
            new SalesOrder("SO-2025-0041", "Globex Corp",       "2025-04-25", "$3 750.00",  "Shipped"),
            new SalesOrder("SO-2025-0040", "Umbrella Ltd",      "2025-04-22", "$28 900.00", "Delivered"),
            new SalesOrder("SO-2025-0039", "Wayne Enterprises", "2025-04-20", "$7 200.00",  "Open"),
            new SalesOrder("SO-2025-0038", "Stark Industries",  "2025-04-18", "$55 000.00", "Delivered")
        ));

        add(new H2("Sales Orders"), new Paragraph("Track and manage customer sales orders."), grid);
        expand(grid);
    }
}
