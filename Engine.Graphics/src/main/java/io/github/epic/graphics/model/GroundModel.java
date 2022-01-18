package io.github.epic.graphics.model;

import static org.lwjgl.opengl.GL30.*;

public final class GroundModel extends BasicModel {

    public GroundModel() {
        super(glGenVertexArrays(), 6);
        init(object);
    }

    @Override
    public void render() {
        glBindVertexArray(object);
        glDrawElements(GL_TRIANGLES, length, GL_UNSIGNED_INT, 0L);
        glBindVertexArray(0);
    }

    private static void init(int object) {
        int[] buffers = new int[2];

        glGenBuffers(buffers);
        glBindVertexArray(object);

        glBindBuffer(GL_ARRAY_BUFFER, buffers[0]);
        glBufferData(GL_ARRAY_BUFFER, new float[]{
                -1.f, -1.f, -1.f, 1.f, 1.f, 1.f, 1.f, -1.f
        }, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, Float.BYTES * 2, 0);
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffers[1]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, new int[]{
                0, 1, 2, 2, 3, 0
        }, GL_STATIC_DRAW);

        glBindVertexArray(0);
        glDeleteBuffers(buffers);
    }

}
