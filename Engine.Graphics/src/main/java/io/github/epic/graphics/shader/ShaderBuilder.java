package io.github.epic.graphics.shader;

import static org.lwjgl.opengl.GL20.*;

public class ShaderBuilder {

    private final int program;
    private final int[] shaders = new int[2];

    public ShaderBuilder() {
        program = glCreateProgram();
    }

    public ShaderBuilder attach(String source, Type type) {
        int compiled = 0;

        if (type == Type.VERTEX_SHADER) {
            compiled = shaders[0] = compile(source, GL_VERTEX_SHADER);
        }

        if (type == Type.FRAGMENT_SHADER) {
            compiled = shaders[1] = compile(source, GL_FRAGMENT_SHADER);
        }

        glAttachShader(program, compiled);

        return this;
    }

    public Shader build() {
        if (shaders[0] == 0)
            throw new IllegalStateException("Vertex shader not attached!");

        if (shaders[1] == 0)
            throw new IllegalStateException("Fragment shader not attached!");

        glLinkProgram(program);

        glDetachShader(program, shaders[0]);
        glDetachShader(program, shaders[1]);

        glDeleteShader(shaders[0]);
        glDeleteShader(shaders[1]);

        return new Shader(program);
    }

    private static int compile(String source, int type) {
        int shader = glCreateShader(type);

        glShaderSource(shader, source);
        glCompileShader(shader);

        if (GL_FALSE == glGetShaderi(shader, GL_COMPILE_STATUS)) {
            String description = glGetShaderInfoLog(shader);

            throw new RuntimeException(
                    String.format("Shader compile error:\n%s\n", description)
            );
        }

        return shader;
    }

    public enum Type {

        VERTEX_SHADER,
        FRAGMENT_SHADER

    }

}
