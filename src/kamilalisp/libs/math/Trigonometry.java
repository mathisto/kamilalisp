package kamilalisp.libs.math;

import ch.obermuhlner.math.big.BigDecimalMath;
import kamilalisp.data.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class Trigonometry {
    public static void install(Environment env) {
        env.push("sin", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'sin'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'sin'", Type.NUMBER);
                    return BigDecimalMath.sin(a.getNumber().get(), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("csec", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'csc'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'csc'", Type.NUMBER);
                    return BigDecimal.valueOf(1).divide(BigDecimalMath.sin(a.getNumber().get(), MathContext.DECIMAL128), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("cos", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'cos'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'cos'", Type.NUMBER);
                    return BigDecimalMath.cos(a.getNumber().get(), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("sec", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'sec'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'sec'", Type.NUMBER);
                    return BigDecimal.valueOf(1).divide(BigDecimalMath.cos(a.getNumber().get(), MathContext.DECIMAL128), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("tan", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'tan'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'tan'", Type.NUMBER);
                    return BigDecimalMath.tan(a.getNumber().get(), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("ctan", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'ctan'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'ctan'", Type.NUMBER);
                    return BigDecimal.valueOf(1).divide(BigDecimalMath.tan(a.getNumber().get(), MathContext.DECIMAL128), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("rad", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'rad'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'rad'", Type.NUMBER);
                    return a.getNumber().get().multiply(BigDecimalMath.pi(MathContext.DECIMAL128).divide(BigDecimal.valueOf(180), MathContext.DECIMAL128));
                }));
            }
        }));

        env.push("deg", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'deg'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'deg'", Type.NUMBER);
                    return a.getNumber().get().multiply(BigDecimal.valueOf(180).divide(BigDecimalMath.pi(MathContext.DECIMAL128), MathContext.DECIMAL128));
                }));
            }
        }));

        env.push("arcsin", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'arcsin'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'arcsin'", Type.NUMBER);
                    return BigDecimalMath.asin(a.getNumber().get(), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("arccos", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'arccos'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'arccos'", Type.NUMBER);
                    return BigDecimalMath.acos(a.getNumber().get(), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("arctan", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 1)
                    throw new Error("Invalid invocation to 'arctan'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    a.guardType("First argument to 'arctan'", Type.NUMBER);
                    return BigDecimalMath.atan(a.getNumber().get(), MathContext.DECIMAL128);
                }));
            }
        }));

        env.push("arctan2", new Atom(new Closure() {
            @Override
            public Atom apply(Executor env, List<Atom> arguments) {
                if(arguments.size() != 2)
                    throw new Error("Invalid invocation to 'arctan2'.");
                return new Atom(new LbcSupplier<>(() -> {
                    Atom a = arguments.get(0);
                    Atom b = arguments.get(1);
                    a.guardType("First argument to 'arctan'", Type.NUMBER);
                    b.guardType("Second argument to 'arctan'", Type.NUMBER);
                    return BigDecimalMath.atan2(a.getNumber().get(), b.getNumber().get(), MathContext.DECIMAL128);
                }));
            }
        }));
    }
}
