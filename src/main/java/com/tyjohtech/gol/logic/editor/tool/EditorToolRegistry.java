package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.util.property.Property;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EditorToolRegistry {

    private HashMap<String, EditorTool> tools = new HashMap<>();
    private Property<List<EditorTool>> availableTools = new Property<>();

    public EditorToolRegistry() {
        availableTools.set(new LinkedList<>());
    }

    public void registerTool(EditorTool editorTool) {
        tools.put(editorTool.name(), editorTool);
        availableTools.get().add(editorTool);
        availableTools.pump();
    }

    public EditorTool get(String toolName) {
        return tools.get(toolName);
    }
}
