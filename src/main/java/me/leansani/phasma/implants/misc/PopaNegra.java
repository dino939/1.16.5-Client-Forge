package me.leansani.phasma.implants.misc;

import me.leansani.phasma.Module;
import me.leansani.phasma.clickgui.settings.BooleanSetting;
import me.leansani.phasma.clickgui.settings.ModeSetting;
import me.leansani.phasma.clickgui.settings.NumberSetting;

public class PopaNegra extends Module {
    public static BooleanSetting testbool = new BooleanSetting("Check",true);
    public static NumberSetting govno = new NumberSetting("SIZE OF BULKA",1,10,5,1);
    public static NumberSetting litersofkal = new NumberSetting("LITRI GOVNA",1,100,50,1);
    public static ModeSetting testmode = new ModeSetting("Mode","1","2","3");

    public PopaNegra() {
        super("PopaNegra",0,Category.MISC,"POPA NEGRA");
        addSetting(govno);
        addSetting(litersofkal);
        addSetting(testmode);
        addSetting(testbool);


    }
}
