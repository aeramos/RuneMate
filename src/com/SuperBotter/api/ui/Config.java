package com.SuperBotter.api.ui;

import com.SuperBotter.api.Methods;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.AbstractBot;
import com.runemate.game.api.script.framework.core.BotPlatform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Config extends GridPane implements Initializable {
    public Config(Object configController, BotPlatform botPlatform, AbstractBot bot) {
        // Load the fxml file using RuneMate's Resources class.
        FXMLLoader loader = new FXMLLoader();

        // Input your Settings GUI FXML file location here.
        // NOTE: DO NOT FORGET TO ADD IT TO MANIFEST AS A RESOURCE
        Future<InputStream> stream = botPlatform.invokeLater(() -> Resources.getAsStream("com/SuperBotter/api/ui/Config.fxml"));

        // Set FlaxFXController as the class that will be handling our events
        //loader.setController(new ConfigController(metaData, configSettings));
        loader.setController(configController);

        // Set the FXML load's root to this class
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException | NullPointerException e) {
            Methods.shutdownBot(bot, "Unable to load GUI. Please restart the bot.", false);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setVisible(true);
    }
}
