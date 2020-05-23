package com.tyjohtech.gol.components.editor.state;

import java.util.HashSet;

public class Edit extends HashSet<Change> {
    public Edit(Edit changes) {
        super(changes);
    }

    public Edit() {
    }
}
