package com.engr.oufoodpantry;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


@Route("editamounts/:itemName")
public class EditAmounts extends VerticalLayout implements BeforeEnterObserver {

    private String itemName; // The name of the item to edit
    private IntegerField amountField; // Field to display and edit the amount

    public EditAmounts() {
        // Initialize components but don't add them to the layout yet since the item name is not known
        amountField = new IntegerField();
        
        Button incrementButton = new Button("+", e -> adjustAmount(1));
        Button decrementButton = new Button("-", e -> adjustAmount(-1));
        
        Button doneButton = new Button("Done", e -> {
            InventoryService.updateItemAmountInJson(itemName, amountField.getValue());
            UI.getCurrent().navigate(MainView.class);
        });

        // Arrange components in the layout
        add(new H1(), incrementButton, amountField, decrementButton, doneButton);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        itemName = event.getRouteParameters().get("itemName").orElse(null);
        if (itemName != null) {
            // Fetch the current amount from the JSON
            Item item = InventoryService.readItemsFromJson().stream()
                          .filter(i -> itemName.equals(i.getName()))
                          .findFirst()
                          .orElseThrow(() -> new IllegalStateException("Item not found"));

            // Set the current amount in the IntegerField
            amountField.setValue(item.getAmount());
        } else {
            // Handle error or redirect
        }
    }
    
    private void adjustAmount(int delta) {
        int currentAmount = amountField.getValue();
        amountField.setValue(currentAmount + delta);
    }
}
