package io.github.epic.core.registry;

import io.github.epic.graphics.model.BasicModel;
import io.github.epic.graphics.shader.Shader;

public class Managers {

    public static final ObjectManager<BasicModel> MODEL_MANAGER;
    public static final ObjectManager<Shader> SHADER_MANAGER;

    static {
        MODEL_MANAGER = new BaseObjectManager<>();
        SHADER_MANAGER = new BaseObjectManager<>();
    }

}
