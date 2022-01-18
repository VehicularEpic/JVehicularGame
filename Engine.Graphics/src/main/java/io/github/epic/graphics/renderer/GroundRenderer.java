package io.github.epic.graphics.renderer;

import io.github.epic.graphics.model.GroundModel;
import io.github.epic.graphics.shader.Shader;
import io.github.epic.graphics.shader.ShaderBuilder;
import io.github.epic.graphics.shader.UniformBufferObject;

import static org.lwjgl.opengl.GL30.*;

public class GroundRenderer {

    private final Shader shader;
    private final GroundModel model;

    private static final String VERTEX_SHADER = "" +
            "#version 330 core\n" +
            "layout (location = 0) in vec2 V;\n" +
            "out vec3 nearPoint;\n" +
            "out vec3 farPoint;\n" +
            "layout (std140) uniform ModelViewProjection\n" +
            "{\n" +
            "    mat4 projection;\n" +
            "    mat4 view;\n" +
            "};\n" +
            "vec3 unproject(vec3 vec) {\n" +
            "    mat4 viewInv = inverse(view);\n" +
            "    mat4 projInv = inverse(projection);\n" +
            "    vec4 unprojectedPoint = viewInv * projInv * vec4(vec, 1.0);\n" +
            "    return unprojectedPoint.xyz / unprojectedPoint.w;\n" +
            "}\n" +
            "void main() {\n" +
            "    nearPoint = unproject(vec3(V.xy, 0.0));\n" +
            "    farPoint = unproject(vec3(V.xy, 1.0));\n" +
            "    gl_Position = vec4(V, 1.f, 1.f);\n" +
            "}\n";

    private static final String FRAGMENT_SHADER = "" +
            "#version 330 core\n" +
            "precision mediump float;\n" +
            "in vec3 nearPoint;\n" +
            "in vec3 farPoint;\n" +
            "layout (location = 0) out vec4 FragColor;\n" +
            "uniform vec3 color;\n" +
            "uniform vec3 lightColor = vec3(1.19, 1.10, 0.91);\n" +
            "void main(void) {\n" +
            "    float t = -nearPoint.y / (farPoint.y - nearPoint.y);\n" +
            "    FragColor = vec4(lightColor * color.rgb, 1.f * float(t > 0));\n" +
            "}\n";

    public GroundRenderer(UniformBufferObject object) {
        this.shader = createShader();
        this.model = new GroundModel();
        shader.uniformBlock("ModelViewProjection", object);
    }

    public void render() {
        shader.use();
        model.render();
        glUseProgram(0);
    }

    public void setColor(float r, float g, float b) {
        shader.use();
        shader.uniform3f("color", new float[]{r, g, b});
        glUseProgram(0);
    }

    private static Shader createShader() {
        return new ShaderBuilder()
                .attach(VERTEX_SHADER, ShaderBuilder.Type.VERTEX_SHADER)
                .attach(FRAGMENT_SHADER, ShaderBuilder.Type.FRAGMENT_SHADER)
                .build();
    }

}
