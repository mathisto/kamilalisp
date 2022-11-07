package palaiologos.kamilalisp.runtime.math;

import ch.obermuhlner.math.big.BigComplex;
import com.google.common.collect.Streams;
import palaiologos.kamilalisp.atom.*;
import palaiologos.kamilalisp.error.ArrayError;

import java.util.List;

public class Lcm extends PrimitiveFunction implements Lambda {
    @Override
    protected String name() {
        return "lcm";
    }

    private static Atom min2(Environment e, Atom a, Atom b) {
        a.assertTypes(Type.REAL, Type.COMPLEX, Type.LIST);
        b.assertTypes(Type.REAL, Type.COMPLEX, Type.LIST);
        if(a.getType() == Type.LIST && b.getType() == Type.LIST) {
            return new Atom(Streams.zip(a.getList().stream(), b.getList().stream(), (x, y) -> min2(e, x, y)).toList());
        } else if(a.getType() == Type.REAL && b.getType() == Type.REAL) {
            Atom gcd = Gcd.gcd2(e, a, b);
            return new Atom(a.getReal().multiply(b.getReal().divide(gcd.getReal(), e.getMathContext())));
        } else if(a.getType() == Type.INTEGER && b.getType() == Type.INTEGER) {
            Atom gcd = Gcd.gcd2(e, a, b);
            return new Atom(a.getInteger().multiply(b.getInteger().divide(gcd.getInteger())));
        } else if(a.getType() == Type.LIST && b.isNumeric()) {
            return new Atom(a.getList().stream().map(x -> min2(e, x, b)).toList());
        } else if(a.isNumeric() && b.getType() == Type.LIST) {
            return new Atom(b.getList().stream().map(x -> min2(e, a, x)).toList());
        } else {
            Atom gcd = Gcd.gcd2(e, a, b);
            BigComplex result;
            if(gcd.getType() == Type.COMPLEX)
                result = a.getComplex().multiply(b.getComplex().divide(gcd.getComplex(), e.getMathContext()));
            else
                result = a.getComplex().multiply(b.getComplex().divide(gcd.getReal(), e.getMathContext()));
            if(result.isReal())
                return new Atom(result.re);
            else
                return new Atom(result);
        }
    }

    @Override
    public Atom apply(Environment env, List<Atom> args) {
        if(args.size() == 1 && args.get(0).getType() == Type.LIST)
            return args.get(0).getList().stream().reduce((a, b) -> min2(env, a, b)).orElseThrow(() -> new ArrayError("can't fold a list with lcm."));
        else if(args.size() <= 1)
            throw new RuntimeException("lcm called with too few arguments.");

        return args.stream().reduce((a, b) -> min2(env, a, b)).orElseThrow(() -> new ArrayError("can't fold a list with lcm."));
    }
}