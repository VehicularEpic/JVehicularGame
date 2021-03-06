package io.github.epic.graphics.shader;

import static org.lwjgl.opengl.GL31.*;

public final class Shader {

    private final int program;

    Shader(int program) {
        this.program = program;
    }

    public void use() {
        glUseProgram(program);
    }

    public void uniform3f(String name, float[] data) {
        int location = glGetUniformLocation(program, name);
        glUniform3fv(location, data);
    }

    public void uniformMatrix4f(String name, float[] data) {
        int location = glGetUniformLocation(program, name);
        glUniformMatrix4fv(location, false, data);
    }

    public void uniformBlock(String name, UniformBufferObject object) {
        int index = glGetUniformBlockIndex(program, name);
        glUniformBlockBinding(program, index, object.getIndex());
    }

}
