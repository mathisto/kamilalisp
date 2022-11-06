package palaiologos.kamilalisp.runtime.math;

import ch.obermuhlner.math.big.BigComplex;
import ch.obermuhlner.math.big.BigDecimalMath;
import palaiologos.kamilalisp.atom.*;
import palaiologos.kamilalisp.error.TypeError;

import java.util.List;

public class Pi extends PrimitiveFunction implements Lambda {
    private static Atom pi(Environment env, Atom x) {
        if(x.getType() == Type.REAL)
            return new Atom(BigDecimalMath.pi(env.getMathContext()).multiply(x.getReal()));
        else if(x.getType() == Type.COMPLEX)
            return new Atom(BigComplex.valueOf(BigDecimalMath.pi(env.getMathContext())).multiply(x.getComplex()));
        else if(x.getType() == Type.LIST)
            return new Atom(x.getList().stream().map(y -> pi(env, y)).toList());
        else
            throw new TypeError("`pi' not defined for: " + x.getType());
    }

    @Override
    public Atom apply(Environment env, List<Atom> args) {
        if(args.isEmpty())
            return new Atom(BigDecimalMath.e(env.getMathContext()));
        else if(args.size() == 1)
            return pi(env, args.get(0));
        else
            return new Atom(args.stream().map(x -> pi(env, x)).toList());
    }

    @Override
    protected String name() {
        return "pi";
    }
}
