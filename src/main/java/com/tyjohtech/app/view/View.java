package com.tyjohtech.app.view;

import com.tyjohtech.gol.components.infobar.InfoBar;

public interface View {

    void addDrawLayer(DrawLayer canvas);

    void setInfoBar(InfoBar node);

    void setCanvas(SimulationCanvas canvas);

    void setToolbar(Toolbar toolbar);
}
