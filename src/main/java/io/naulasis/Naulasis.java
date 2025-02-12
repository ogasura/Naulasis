package io.naulasis;

import imgui.*;
import imgui.flag.ImDrawFlags;
import imgui.flag.ImGuiWindowFlags;
import io.naulasis.utils.ColorConverter;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Naulasis {

    private static Naulasis instance;

    // NOTE: Made this static because of JNI!
    private static long window;
    private String currentPath = System.getProperty("user.home");
    @Getter
    @Setter private static ImFont iconsFont = null;

    public void init(long window) {
        Naulasis.window = window;
        System.out.println("Initializing Naulasis...");

        setIconsFont(ImGui.getIO().getFonts().addFontFromFileTTF(System.getProperty("user.home") + "/Desktop/Projects/Recoding/Naulasis/src/main/resources/fonts/Glyphter.ttf", 30));
        ImGuiIO io = ImGui.getIO();
        ImFontAtlas fontAtlas = io.getFonts();

        fontAtlas.build();
    }

    public long getWindow(){
        return window;
    }

    public static Naulasis getInstance() {
        if (instance == null) {
            instance = new Naulasis();
        }

        return instance;
    }
    //, boolean resizable, boolean moveable, boolean closeable, boolean hideable
    public static void begin(String title) {
        ImGui.begin(title, ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoMove);
        ImGui.getStyle().setWindowRounding(10);

        ImDrawList drawList = ImGui.getForegroundDrawList();
        drawList.addRectFilled(new ImVec2(ImGui.getWindowPosX(), ImGui.getWindowPosY()), new ImVec2(ImGui.getWindowPosX() + ImGui.getWindowSizeX(), ImGui.getWindowPosY() + 30), ColorConverter.colorToInt(30, 30, 30, 255), 10, ImDrawFlags.RoundCornersAll);
    }

    public static void end(){
        ImGui.end();
    }

    public static float getWindowMousePosX(){
        return ImGui.getMousePosX() - ImGui.getWindowPosX();
    }

    public static float getWindowMousePosY(){
        return ImGui.getMousePosY() - ImGui.getWindowPosY();
    }

}
