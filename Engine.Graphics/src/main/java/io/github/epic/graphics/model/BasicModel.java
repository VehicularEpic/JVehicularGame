package io.github.epic.graphics.model;

import static org.lwjgl.opengl.GL30.*;

public class BasicModel {

    protected final int object;
    protected final int length;

    BasicModel(int object, int length) {
        this.object = object;
        this.length = length;
    }

    public void render() {
        glBindVertexArray(this.object);
        glDrawArrays(GL_TRIANGLES, 0, this.length);
        glBindVertexArray(0);
    }

}
