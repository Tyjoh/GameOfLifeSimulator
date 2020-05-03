package com.tyjohtech.gol;

import com.tyjohtech.app.command.CommandExecutor;
import com.tyjohtech.app.event.EventBus;
import com.tyjohtech.app.state.StateRegistry;
import com.tyjohtech.app.view.MainView;
import com.tyjohtech.app.view.Toolbar;
import com.tyjohtech.gol.components.board.BoardComponentFactory;
import com.tyjohtech.gol.components.editor.EditorComponentFactory;
import com.tyjohtech.gol.components.infobar.InfoBarComponentFactory;
import com.tyjohtech.gol.components.simulator.SimulatorComponentFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class App2 extends Application {

    @Override
    public void start(Stage stage) {
        StateRegistry stateRegistry = new StateRegistry();
        CommandExecutor commandExecutor = new CommandExecutor(stateRegistry);
        EventBus eventBus = new EventBus();
        MainView view = new MainView(eventBus);

        ApplicationContext context = new ApplicationContext(
                stateRegistry, eventBus, commandExecutor,
                view, 20, 12);

        List<ComponentFactory> components = new LinkedList<>();
        components.add(new BoardComponentFactory());
        components.add(new EditorComponentFactory());
        components.add(new SimulatorComponentFactory());
        components.add(new InfoBarComponentFactory());

        for (ComponentFactory component : components) {
            component.createState(context);
        }

        for (ComponentFactory component : components) {
            component.createView(context);
        }

        Toolbar toolbar = new Toolbar(eventBus);
        view.setTop(toolbar);

        Scene scene = new Scene(view, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
