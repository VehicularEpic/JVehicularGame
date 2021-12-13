package io.github.epic.core.io;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public final class ArchiveManager {

    private static final Map<String, Repository> REPOSITORIES = new HashMap<>();

    private ArchiveManager() {
    }

    public static Repository getRepository(String name) {
        if (REPOSITORIES.containsKey(name))
            return REPOSITORIES.get(name);

        Repository repository = new Repository(name);
        REPOSITORIES.put(name, repository);

        return repository;
    }

    public static class Repository {

        private final String name;

        private Repository(String name) {
            this.name = name;
        }

        public ByteBuffer read(String fileName) {
            try (InputStream stream = new FileInputStream(name + '/' + fileName)) {
                return ByteBuffer.wrap(IOUtils.toByteArray(stream))
                        .order(ByteOrder.nativeOrder());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return ByteBuffer.allocate(0);
        }

    }

}
