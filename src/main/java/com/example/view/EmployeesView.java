package com.example.view;

import com.example.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Employees")
@Route(value = "employees", layout = MainLayout.class)
public class EmployeesView extends VerticalLayout {

    record Employee(String name, String department, String jobTitle, String email, String phone) {}

    public EmployeesView() {
        setSizeFull();
        setPadding(true);

        Grid<Employee> grid = new Grid<>(Employee.class, false);
        grid.addColumn(Employee::name).setHeader("Name").setAutoWidth(true);
        grid.addColumn(Employee::department).setHeader("Department").setAutoWidth(true);
        grid.addColumn(Employee::jobTitle).setHeader("Job Title").setAutoWidth(true);
        grid.addColumn(Employee::email).setHeader("Email").setAutoWidth(true);
        grid.addColumn(Employee::phone).setHeader("Phone").setAutoWidth(true);
        grid.setItems(List.of(
            new Employee("Alice Brown",    "Finance",    "CFO",               "alice@erp.com",   "+1-555-1001"),
            new Employee("David Clark",    "Sales",      "Sales Manager",     "david@erp.com",   "+1-555-1002"),
            new Employee("Emma Wilson",    "Purchasing", "Procurement Lead",  "emma@erp.com",    "+1-555-1003"),
            new Employee("Frank Miller",   "Warehouse",  "Warehouse Manager", "frank@erp.com",   "+1-555-1004"),
            new Employee("Grace Lee",      "IT",         "System Admin",      "grace@erp.com",   "+1-555-1005")
        ));

        add(new H2("Employees"), new Paragraph("Manage employee records and assignments."), grid);
        expand(grid);
    }
}
