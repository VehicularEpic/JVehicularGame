package io.github.epic.graphics;

import io.github.epic.graphics.projection.CameraView;
import io.github.epic.graphics.projection.ProjectionStrategy;

public interface RenderContext {

    void init(RenderEngine engine);

    void render(RenderEngine engine, ProjectionStrategy strategy, CameraView view);

}
