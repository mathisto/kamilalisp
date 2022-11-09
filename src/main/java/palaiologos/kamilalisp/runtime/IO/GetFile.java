package palaiologos.kamilalisp.runtime.IO;

import palaiologos.kamilalisp.atom.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GetFile extends PrimitiveFunction implements Lambda {
    @Override
    protected String name() {
        return "get-file";
    }

    @Override
    public Atom apply(Environment env, List<Atom> args) {
        assertArity(args, 1);
        Atom arg = args.get(0);
        arg.assertTypes(Type.STRING);
        String fileName = arg.getString();
        try {
            return new Atom(Files.readString(Path.of(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}