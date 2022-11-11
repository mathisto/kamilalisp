package palaiologos.kamilalisp.runtime.array;

import palaiologos.kamilalisp.atom.*;

import java.math.BigInteger;
import java.util.List;

public class IndexOf extends PrimitiveFunction implements Lambda {
    private static Atom indexOf(Atom a, Atom b) {
        if (a.getType() == Type.LIST && b.getType() == Type.LIST) {
            return new Atom(a.getList().stream().map(x -> indexOf(x, b)).toList());
        } else if (b.getType() == Type.LIST) {
            return new Atom(BigInteger.valueOf(b.getList().indexOf(a)));
        } else {
            throw new RuntimeException("indexOf takes a list and an atom as arguments");
        }
    }

    @Override
    public Atom apply(Environment env, List<Atom> args) {
        assertArity(args, 2);
        return indexOf(args.get(0), args.get(1));
    }

    @Override
    protected String name() {
        return "index-of";
    }
}
