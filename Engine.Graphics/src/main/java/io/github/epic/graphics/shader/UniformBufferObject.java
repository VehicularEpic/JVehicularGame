package io.github.epic.graphics.shader;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL31.*;

public class UniformBufferObject {

    private final int index;
    private final int buffer;

    public UniformBufferObject(int index, int count) {
        this.index = index;
        this.buffer = glGenBuffers();
        init(count);
    }

    private void init(int count) {
        glBindBuffer(GL_UNIFORM_BUFFER, buffer);
        glBufferData(GL_UNIFORM_BUFFER, Float.BYTES * 16L * count, GL_STATIC_DRAW);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);

        glBindBufferBase(GL_UNIFORM_BUFFER, index, buffer);
    }

    public void put(int i, float[] data) {
        glBindBuffer(GL_UNIFORM_BUFFER, buffer);
        glBufferSubData(GL_UNIFORM_BUFFER, Float.BYTES * 16L * i, data);
        glBindBuffer(GL_UNIFORM_BUFFER, 0);
    }

    public int getIndex() {
        return index;
    }

}
