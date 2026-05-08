package com.example;

import com.example.view.CustomersView;
import com.example.view.DashboardView;
import com.example.view.EmployeesView;
import com.example.view.ProductsView;
import com.example.view.SalesOrdersView;
import com.example.view.SuppliersView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    public MainLayout() {
        setPrimarySection(Section.DRAWER);

        H1 appTitle = new H1("ERP System");
        appTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        addToNavbar(true, new DrawerToggle(), appTitle);

        Scroller scroller = new Scroller(buildMenu());
        scroller.addClassNames(LumoUtility.Padding.SMALL);
        addToDrawer(scroller);
    }

    private static SideNav buildMenu() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Dashboard", DashboardView.class,
                VaadinIcon.HOME.create()));

        // Master Data
        SideNavItem masterData = new SideNavItem("Master Data");
        masterData.setPrefixComponent(VaadinIcon.RECORDS.create());
        masterData.addItem(new SideNavItem("Customers",  CustomersView.class));
        masterData.addItem(new SideNavItem("Suppliers",  SuppliersView.class));
        masterData.addItem(new SideNavItem("Products",   ProductsView.class));
        masterData.addItem(new SideNavItem("Employees",  EmployeesView.class));
        nav.addItem(masterData);

        // Sales
        SideNavItem sales = new SideNavItem("Sales");
        sales.setPrefixComponent(VaadinIcon.MONEY.create());
        sales.addItem(new SideNavItem("Sales Orders", SalesOrdersView.class));
        nav.addItem(sales);

        return nav;
    }
}
