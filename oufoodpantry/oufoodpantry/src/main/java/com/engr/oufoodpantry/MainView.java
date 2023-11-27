package com.engr.oufoodpantry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.List;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;


@CssImport("./styles/styles.css")
@Route("/")
public class MainView extends VerticalLayout {

    public MainView() {
        addClassName("dark-background");
        List<Item> items = InventoryService.readItemsFromJson();
        
        // Top Navigation Bar
        Button addItemButton = new Button("Add Item");
        addItemButton.addClassName("crimson-button");
        addItemButton.addClickListener(event -> {
            event.getSource().getUI().ifPresent(ui -> 
            ui.navigate("add-item"));
        });
        
        Button scanButton = new Button("Scan - (W.I.P)");
        addItemButton.addClassName("crimson-button");
        scanButton.addClickListener(event -> {
            // Logic for scanning
        });

        HorizontalLayout topBar = new HorizontalLayout(addItemButton, scanButton);
        add(topBar); // Add to main view

        // List of Items
        for (Item item : items) {
            add(createItemRow(item));
        }


        add(new TextField("University of Oklahoma Food Pantry"));
    }

// Helper method to create a row for an item
private HorizontalLayout createItemRow(Item item) {
    TextField itemNameField = new TextField();
    itemNameField.setValue(item.getName());
    itemNameField.setReadOnly(true);

    TextField countField = new TextField();
    countField.setValue(String.valueOf(item.getAmount()));
    countField.setReadOnly(true);

    Button deleteButton = new Button("Delete");
    deleteButton.addClickListener(e -> {
        InventoryService.deleteItemFromJson(item.getName());
        // Reload the UI
        getUI().ifPresent(ui -> ui.getPage().reload());
    });
    // Inside createItemRow method in MainView.java

    Button editButton = new Button("Edit", e -> {
    // You will pass the item name as a parameter to the EditAmounts view
    UI.getCurrent().navigate("editamounts/" + item.getName());
    });
    return new HorizontalLayout(itemNameField, countField, editButton, deleteButton);
}

}
