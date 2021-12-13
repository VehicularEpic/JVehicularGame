package io.github.epic.core;

import io.github.epic.commons.Camera;
import io.github.epic.commons.Entity;
import io.github.epic.commons.World;
import io.github.epic.core.io.ArchiveManager;
import io.github.epic.core.registry.Managers;
import io.github.epic.graphics.RenderContext;
import io.github.epic.graphics.RenderEngine;
import io.github.epic.graphics.gui.UserWindow;
import io.github.epic.graphics.model.ModelBuilder;
import io.github.epic.graphics.projection.CameraView;
import io.github.epic.graphics.projection.EntityMatrix;
import io.github.epic.graphics.projection.PerspectiveProjection;
import io.github.epic.graphics.projection.ProjectionStrategy;
import io.github.epic.graphics.shader.Shader;
import io.github.epic.graphics.shader.ShaderBuilder;
import io.github.epic.graphics.shader.UniformBufferObject;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Game implements RenderContext {

    private final UserWindow window;
    private final UniformBufferObject uniform = new UniformBufferObject(1, 2);

    private World world;

    public Game(UserWindow window) {
        this.window = window;
    }

    @Override
    public void init(RenderEngine engine) {
        PerspectiveProjection projection = new PerspectiveProjection();
        projection.setFarPlane(100000.d);
        engine.setProjectionStrategy(projection);

        Shader sceneShader = Managers.SHADER_MANAGER.get("scene");
        sceneShader.uniformBlock("ModelViewProjection", uniform);


        MapLoader loader = new MapLoader("62");
        loader.load();

        float[] sky = loader.getSkyColor();
        engine.setClearColor(sky[0], sky[1], sky[2]);

        this.world = loader.getWorld();
    }

    @Override
    public void render(RenderEngine engine, ProjectionStrategy strategy, CameraView view) {
        Camera camera = view.getCamera();
        camera.translation(0.d, 10.d, -100.d);
        camera.rotate(0, 0, 0.001);

        float[] projection = strategy.project(
                window.getContentWidth(), window.getContentHeight()
        );

        uniform.put(0, projection);
        uniform.put(1, view.getMatrix());

        Shader sceneShader = Managers.SHADER_MANAGER.get("scene");
        sceneShader.use();

        for (Entity entity : world.getEntities()) {
            EntityMatrix matrix = new EntityMatrix(entity);

            sceneShader.uniformMatrix4f("model", matrix.getValues());
            Managers.MODEL_MANAGER.get(entity.getName()).render();
        }
    }

    public static void main(String[] args) {
        UserWindow window = new UserWindow("The Game");

        loadModels();
        loadShader("scene");

        RenderEngine engine = new RenderEngine(window);
        engine.run(new Game(window));
    }

    private static void loadModels() {
        ArchiveManager.Repository repository = ArchiveManager.getRepository("assets/models");
        final String[] legacy = {
                "prod", "prodw", "prodb2", "prodb1", "prodt", "drod", "grod", "drodt", "nrod", "nrodt",
                "mixpd", "mixnd", "mixpn", "prode", "drode", "pipg", "prmp", "prmpc", "prmpg", "prmpm",
                "prmpw", "prmpb", "prmps", "dbmp", "drmpb", "drmps", "pipe", "spikes", "bemd", "brdr",
                "chk", "fix", "dchk", "drodb", "drodbb", "bprmpup", "prmpup", "wal", "fenc",
                "prmpl", "net", "prmpspd", "drmpg", "tiny", "dhil", "stunl", "tunl", "lift", "mountn",
                "pyrmx", "dpil1", "dpil2", "brdr2", "tre1", "tre2", "tre3", "tre4", "tre5",
                "tre6", "tre7", "tre8", "cac1", "cac2", "cac3", "blok", "pyrmd", "tub"
        };

        for (String name : legacy) {
            ByteBuffer buffer = repository.read(name + ".dat");
            int length = buffer.getInt();

            ModelBuilder builder = new ModelBuilder().data(buffer)
                    .attrib(3, 10, 0)
                    .attrib(3, 10, 3)
                    .attrib(4, 10, 6);

            Managers.MODEL_MANAGER.save(name, builder.build(length));
        }
    }

    private static void loadShader(String name) {
        ArchiveManager.Repository repository = ArchiveManager.getRepository("assets/shaders");

        String vs = Charset.defaultCharset().decode(
                repository.read(name + ".vs")
        ).toString();

        String fs = Charset.defaultCharset().decode(
                repository.read(name + ".fs")
        ).toString();

        ShaderBuilder builder = new ShaderBuilder()
                .attach(vs, ShaderBuilder.Type.VERTEX_SHADER)
                .attach(fs, ShaderBuilder.Type.FRAGMENT_SHADER);
        Managers.SHADER_MANAGER.save(name, builder.build());
    }

}
