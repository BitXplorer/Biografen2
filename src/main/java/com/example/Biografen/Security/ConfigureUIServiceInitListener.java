package com.example.Biografen.Security;

import com.example.Biografen.Views.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }


    /**
     * Reroutes the user if they´re not authorized to access the view.
     *
     * @param event
     *              before navigation event with event details
     */
    //TODO - ändra här
    private void authenticateNavigation(BeforeEnterEvent event) {

        /*
        if (!SecurityUtils.isAccessGranted(event.getNavigationTarget())) { // (1)
            if (SecurityUtils.isUserLoggedIn()) { // (2)
                event.rerouteToError(NotFoundException.class); // (3)
            } else {
                event.rerouteTo(LoginView.class); // (4)
            }
        }
         */

        if (!LoginView.class.equals(event.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }
}
