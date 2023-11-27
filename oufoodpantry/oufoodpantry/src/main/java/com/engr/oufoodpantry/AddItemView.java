package com.engr.oufoodpantry;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.notification.Notification;
import java.net.URISyntaxException;


@PageTitle("Add Item")
@Route("add-item")
public class AddItemView extends VerticalLayout {

    public AddItemView() {
        // Text field for item input
        TextField itemNameField = new TextField("Item Name");

        // Button is declared and instantiated before being used in the lambda
        Button doneButton = new Button("Done");
        //TODO Add cancel button
        doneButton.addClickListener(event -> {
            // Navigate back to MainView when done
            String itemName = itemNameField.getValue();
                //  TODO: ******BUG****** When duplicate items are added, causes problems. Check for duplicates
                if(itemName != null && !itemName.trim().isEmpty()){
                    //CALL METHOD FOR ADDING ITEMNAME TO JSON
                    InventoryService.addItemToJson(itemName);
                    //MAYBE HAVE TO CALL READFILES AGAIN?
                    Notification.show("Item added: " + itemName);
                    doneButton.getUI().ifPresent(ui ->
                    ui.navigate("/"));
            }
        });

        // Add components to the layout
        add(itemNameField, doneButton);
    }
}
