package io.github.epic.core;

import io.github.epic.commons.Entity;
import io.github.epic.commons.World;
import io.github.epic.core.io.ArchiveManager;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class MapLoader {

    private static final String[] LEGACY = {
            "prod", "prodw", "prodb2", "prodb1", "prodt", "drod", "grod", "drodt", "nrod", "nrodt",
            "mixpd", "mixnd", "mixpn", "prode", "drode", "pipg", "prmp", "prmpc", "prmpg", "prmpm",
            "prmpw", "prmpb", "prmps", "dbmp", "drmpb", "drmps", "pipe", "spikes", "bemd", "brdr",
            "chk", "fix", "dchk", "drodb", "drodbb", "bprmpup", "prmpup", "start", "wal", "fenc",
            "prmpl", "net", "prmpspd", "drmpg", "tiny", "dhil", "stunl", "tunl", "lift", "mountn",
            "pyrmx", "cres", "dpil1", "dpil2", "brdr2", "tre1", "tre2", "tre3", "tre4", "tre5",
            "tre6", "tre7", "tre8", "cac1", "cac2", "cac3", "blok", "ful", "pyrmd", "tub"
    };

    private final String name;
    private final World world;

    private String mapName;
    private float[] skyColor = {0.f, 0.f, 0.f};
    private float[] groundColor = {0.f, 0.f, 0.f};

    public MapLoader(String name) {
        this.name = name;
        this.world = new World();
    }

    public void load() {
        ArchiveManager.Repository repository = ArchiveManager.getRepository("assets/maps");
        ByteBuffer buffer = repository.read(name + ".dat");

        if (buffer.capacity() == 0)
            throw new RuntimeException(
                    String.format("Error loading map '%s'", name)
            );

        mapName = readString(buffer, 64);

        skyColor = new float[]{
                buffer.getFloat(),
                buffer.getFloat(),
                buffer.getFloat(),
        };

        groundColor = new float[]{
                buffer.getFloat(),
                buffer.getFloat(),
                buffer.getFloat(),
        };

        int nobjects = buffer.getInt();

        for (int i = 0; i < nobjects; i++) {
            readString(buffer, 4);
            int id = buffer.getInt();

            Entity entity = new Entity(LEGACY[id]);
            entity.translation(buffer.getFloat(), buffer.getFloat(), buffer.getFloat());
            entity.rotation(0, 0, Math.toRadians(buffer.getInt()));

            world.addEntity(entity);
        }
    }

    public World getWorld() {
        return world;
    }

    public String getMapName() {
        return mapName;
    }

    public float[] getSkyColor() {
        return skyColor;
    }

    public float[] getGroundColor() {
        return groundColor;
    }

    private static String readString(ByteBuffer buffer, int size) {
        byte[] name = new byte[size];
        buffer.get(name, 0, name.length);

        return new String(name, Charset.defaultCharset());
    }

}
