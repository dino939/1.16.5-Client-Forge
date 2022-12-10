package me.leansani.phasma.hwid;

import java.util.ArrayList;
import java.util.List;

public class HWIDManager {
    public static final String pastebinURL = "https://pastebin.com/raw/8ZEQ5daxx";

    public static List<String> hwids = new ArrayList<>();

    public static void hwidCheck() {
        hwids = URLReader.readURL();
        boolean isHwidPresent = hwids.contains(SystemUtil.getSystemInfo());
        if (!isHwidPresent) {
            DisplayUtil.Display();
            throw new NoStackTraceThrowable("");
        }
    }
}
