package palaiologos.kamilalisp.runtime.flt64;

import palaiologos.kamilalisp.atom.*;

import java.util.ArrayList;
import java.util.List;

import static palaiologos.kamilalisp.runtime.flt64.Flt64AtomThunk.toAtom;
import static palaiologos.kamilalisp.runtime.flt64.Flt64AtomThunk.toFloat;

public abstract class Flt64TriadicFunction extends PrimitiveFunction implements Lambda {
    private String name;

    public Flt64TriadicFunction(String name) {
        this.name = name;
    }

    public abstract double apply(double x, double y, double z);

    @Override
    public Atom apply(Environment env, List<Atom> args) {
        if (args.size() != 3)
            throw new IllegalArgumentException("Expected 3 arguments, got " + args.size());
        return toAtom(apply(toFloat(args.get(0)), toFloat(args.get(1)), toFloat(args.get(2))));
    }

    @Override
    public String name() {
        return name;
    }
}
