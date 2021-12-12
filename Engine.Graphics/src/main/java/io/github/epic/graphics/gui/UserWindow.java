package io.github.epic.graphics.gui;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class UserWindow {

    private final long glfwWindow;

    private int width = 800;
    private int height = 600;

    private int contentWidth = 0;
    private int contentHeight = 0;

    public UserWindow(String title) {
        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);

        if (glfwWindow == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetInputMode(glfwWindow, GLFW_LOCK_KEY_MODS, GLFW_TRUE);
        glfwMakeContextCurrent(glfwWindow);

        GL.createCapabilities();
        glfwSwapInterval(1);

        glfwSetWindowSizeCallback(glfwWindow, (window, w, h) -> {
            width = w;
            height = h;
        });

        glfwSetFramebufferSizeCallback(glfwWindow, (window, w, h) ->
                glViewport(0, 0, contentWidth = w, contentHeight = h)
        );
    }

    public void show() {
        this.show(true);
    }

    public void show(boolean maximized) {
        if (maximized)
            glfwMaximizeWindow(glfwWindow);

        glfwShowWindow(glfwWindow);
    }

    public long getWindowPointer() {
        return glfwWindow;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        glfwSetWindowSize(glfwWindow, this.width = width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        glfwSetWindowSize(glfwWindow, width, this.height = height);
    }

    public int getContentWidth() {
        return contentWidth;
    }

    public int getContentHeight() {
        return contentHeight;
    }

    static {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
    }

}
