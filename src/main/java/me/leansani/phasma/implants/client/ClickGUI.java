package me.leansani.phasma.implants.client;

import me.leansani.phasma.Module;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", 0, Category.CLIENT, "Opens this menu");
    }

    @Override
    public void onEnable() {
        //mc.setScreen(new ClickGUIScreen((ITextComponent) this));
    }
}
