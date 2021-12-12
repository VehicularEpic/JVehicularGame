package io.github.epic.graphics.model;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL30.*;

public class ModelBuilder {

    private final int buffer;
    private final int object;

    private int attrib = 0;

    public ModelBuilder() {
        buffer = glGenBuffers();
        object = glGenVertexArrays();
    }

    public ModelBuilder data(ByteBuffer data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.capacity());

        while (data.hasRemaining())
            buffer.put(data.getFloat());

        return data(buffer.flip());
    }

    public ModelBuilder data(FloatBuffer data) {
        glBindVertexArray(object);

        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);

        glBindVertexArray(0);

        return this;
    }

    public ModelBuilder attrib(int size, int elements, int offset) {
        glBindVertexArray(object);

        glEnableVertexAttribArray(attrib);
        glVertexAttribPointer(
                attrib, size, GL_FLOAT, false, Float.BYTES * elements, (long) Float.BYTES * offset
        );

        glBindVertexArray(0);
        attrib++;

        return this;
    }

    public BasicModel build(int length) {
        glDeleteBuffers(buffer);
        return new BasicModel(object, length);
    }

}
