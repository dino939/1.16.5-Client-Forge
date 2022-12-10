package me.leansani.phasma.utils;

import me.leansani.phasma.Phasma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileUtil {

    public static String getShaderSource(String fileName) {
        String source = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Phasma.class.getResourceAsStream("/assets/minecraft/shaders/" + fileName))));
        source = bufferedReader.lines().filter(str -> !str.isEmpty()).map(str -> str = str.replace("\t", "")).collect(Collectors.joining("\n"));
        try {
            bufferedReader.close();
        } catch (IOException ignored) {

        }
        return source;
    }

}
