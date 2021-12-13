package io.github.epic.graphics;

import io.github.epic.graphics.gui.UserWindow;
import io.github.epic.graphics.projection.CameraView;
import io.github.epic.graphics.projection.PerspectiveProjection;
import io.github.epic.graphics.projection.ProjectionStrategy;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;

public final class RenderEngine {

    private final UserWindow window;
    private final CameraView cameraView = new CameraView();

    private ProjectionStrategy projectionStrategy = new PerspectiveProjection();

    public RenderEngine(UserWindow window) {
        this.window = window;
    }

    public void run(RenderContext context) {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_MULTISAMPLE);

        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        context.init(this);
        window.show();

        final long glfwWindow = window.getWindowPointer();
        while (!glfwWindowShouldClose(glfwWindow)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            context.render(this, projectionStrategy, cameraView);
            glUseProgram(0);

            glfwSwapBuffers(glfwWindow);
            glfwPollEvents();
        }
    }

    public void setProjectionStrategy(ProjectionStrategy projectionStrategy) {
        this.projectionStrategy = projectionStrategy;
    }

    public void setClearColor(float r, float g, float b) {
        glClearColor(r, g, b, 1.f);
    }

}
