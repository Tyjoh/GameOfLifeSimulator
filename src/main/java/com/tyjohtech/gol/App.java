package com.tyjohtech.gol;

import com.tyjohtech.gol.logic.editor.EditorModelFactory;
import com.tyjohtech.gol.logic.editor.tool.ToolModelFactory;
import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.model.SimulationModelFactory;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.command.RootCommandProcessor;
import com.tyjohtech.gol.util.event.BasicEventBus;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelProvider;
import com.tyjohtech.gol.view.MainView;
import com.tyjohtech.gol.view.ViewFactory;
import com.tyjohtech.gol.view.infobar.InfoBarFactory;
import com.tyjohtech.gol.view.settings.SimulationSettingsFactory;
import com.tyjohtech.gol.view.simulation.SimulationCanvasFactory;
import com.tyjohtech.gol.view.toolbar.ToolbarFactory;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        EventBus eventBus = new BasicEventBus();
        ModelProvider modelProvider = new ModelProvider();
        CommandProcessor commandProcessor = new RootCommandProcessor();
        MainView mainView = new MainView();

        List<ModelFactory> modelFactories = new LinkedList<>();
        modelFactories.add(new EditorModelFactory());
        modelFactories.add(new ToolModelFactory());
        modelFactories.add(new SimulationModelFactory());

        for (ModelFactory modelFactory : modelFactories) {
            modelFactory.initialize(modelProvider, eventBus, commandProcessor);
        }

        List<ViewFactory> viewFactories = new LinkedList<>();
        viewFactories.add(new SimulationCanvasFactory());
        viewFactories.add(new InfoBarFactory());
        viewFactories.add(new ToolbarFactory());
        viewFactories.add(new SimulationSettingsFactory());

        for (ViewFactory viewFactory : viewFactories) {
            Node node = viewFactory.buildView(modelProvider, eventBus);
            switch (viewFactory.getMainViewPosition()) {
                case TOOLBAR:
                    mainView.setTop(node);
                    break;
                case LEFT:
                    mainView.setLeft(node);
                    break;
                case RIGHT:
                    mainView.setRight(node);
                    break;
                case BOTTOM:
                    mainView.setBottom(node);
                    break;
                case CENTER:
                    mainView.setCenter(node);
                    break;
                default:
                    System.out.println("Unsupported view position: " + viewFactory.getMainViewPosition().name());
            }
        }


//        stage.addEventHandler(KeyEvent.ANY, new ToolSelectHandler(boardEditor, commandProcessor));
//        simulationCanvas.addEventHandler(MouseEvent.ANY, new SimulationCanvasEventHandler(boardEditor, simulationCanvasViewModel));


        Scene scene = new Scene(mainView, 1200, 800);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}