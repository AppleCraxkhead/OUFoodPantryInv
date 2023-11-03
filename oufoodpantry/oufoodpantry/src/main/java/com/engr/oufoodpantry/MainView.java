package com.engr.oufoodpantry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("dashboard") // This is where in the URL the page is. For example, if we were youtube.com, youtube.com/dashboard would go to this page
public class MainView extends VerticalLayout { // Each "View" or page in the app has its own class.

    public MainView() { // This method contains all of the components, such as buttons for the view

        Button button1 = new Button("Click me"); // Looks kinda like declaring a new scanner object. Button is the variable or object type, I then name it "button1". The button will say "Click me" on it

        button1.addClickListener(event -> { // This adds a listener to the button. A listener "listens" for the object being interacted with, in this case a button press.
            button1.setText("clicked"); // This is the code that is excecuted when the listener notices the button is pressed. 
        });
        add(button1); //this makes the button actually appear on the screen. 
    }


}