package palaiologos.kamilalisp.runtime.lib;

import palaiologos.kamilalisp.atom.Atom;
import palaiologos.kamilalisp.atom.Environment;
import palaiologos.kamilalisp.runtime.cas.*;
import palaiologos.kamilalisp.runtime.math.*;
import palaiologos.kamilalisp.runtime.math.bit.*;
import palaiologos.kamilalisp.runtime.math.cmp.*;
import palaiologos.kamilalisp.runtime.math.hyperbolic.*;
import palaiologos.kamilalisp.runtime.math.trig.*;
import palaiologos.kamilalisp.runtime.meta.Match;
import palaiologos.kamilalisp.runtime.string.Ucb;
import palaiologos.kamilalisp.runtime.string.Ucs;

public class MathLib {
    public static void register(Environment env) {
        env.setPrimitive("bit:and", "⌶∧", new Atom(new BitAnd()));
        env.setPrimitive("bit:or", "⌶∨", new Atom(new BitOr()));
        env.setPrimitive("bit:xor", "⌶≠", new Atom(new BitXor()));
        env.setPrimitive("bit:nand", "⌶¬∧", new Atom(new BitNand()));
        env.setPrimitive("bit:not", "⌶¬", new Atom(new BitNot()));
        env.setPrimitive("bit:popcount", "⌶⍏", new Atom(new BitPopcount()));
        env.setPrimitive("bit:unpack", "⌶↓", new Atom(new BitUnpack()));
        env.setPrimitive("bit:pack", "⌶↑", new Atom(new BitPack()));

        env.setPrimitive("cas:integral", "∫", new Atom(new Integral()));
        env.setPrimitive("cas:complex-integral", "⍉∫", new Atom(new ComplexIntegral()));
        env.setPrimitive("cas:fn", "ƒ", new Atom(new Fn()));
        env.setPrimitive("cas:lim", "↘↖", new Atom(new Limit()));
        env.setPrimitive("cas:complex-lim", "⍉↘↖", new Atom(new Limit()));
        env.setPrimitive("cas:N", "∅ƒ", new Atom(new Numeric()));
        env.setPrimitive("cas:D", "∇1", new Atom(new Derivative()));
        env.setPrimitive("cas:taylor", "⍟∇", new Atom(new Taylor()));
        env.setPrimitive("cas:puiseux", "⍧∇", new Atom(new Puiseux()));
        env.setPrimitive("cas:laurent", "⍉∇", new Atom(new Laurent()));
        env.setPrimitive("cas:maclaurin", "∇0", new Atom(new Maclaurin()));
        env.setPrimitive("cas:polynomial", "⊕ƒ⊗", new Atom(new Polynomial()));
        env.setPrimitive("cas:factor", "ƒ∵", new Atom(new Factor()));
        env.setPrimitive("cas:nabla", "∇∧", new Atom(new Nabla()));
        env.setPrimitive("cas:simp", "ƒ⊙", new Atom(new Simp()));
        env.setPrimitive("cas:fn:vars", "ƒ⍴", new Atom(new MfnVariables()));

        env.setPrimitive("abs", new Atom(new Abs()));
        env.setPrimitive("bernoulli", new Atom(new Bernoulli()));
        env.setPrimitive("binomial", new Atom(new Binomial()));
        env.setPrimitive("**", new Atom(new DoubleStar()));
        env.setPrimitive("ln", new Atom(new Ln()));
        env.setPrimitive("sqrt", "√", new Atom(new Sqrt()));
        env.setPrimitive(">", new Atom(new Gt()));
        env.setPrimitive("<", new Atom(new Lt()));
        env.setPrimitive(">=", "≥", new Atom(new Ge()));
        env.setPrimitive("<=", "≤", new Atom(new Le()));
        env.setPrimitive("sin", new Atom(new Sin()));
        env.setPrimitive("cos", new Atom(new Cos()));
        env.setPrimitive("tan", new Atom(new Tan()));
        env.setPrimitive("asin", new Atom(new Asin()));
        env.setPrimitive("acos", new Atom(new Acos()));
        env.setPrimitive("atan", new Atom(new Atan()));
        env.setPrimitive("re", new Atom(new Re()));
        env.setPrimitive("complex-parts", new Atom(new ComplexParts()));
        env.setPrimitive("im", new Atom(new Im()));
        env.setPrimitive("o", new Atom(new O()));
        env.setPrimitive("csc", new Atom(new Csc()));
        env.setPrimitive("sec", new Atom(new Sec()));
        env.setPrimitive("cot", new Atom(new Cot()));
        env.setPrimitive("acsc", new Atom(new Acsc()));
        env.setPrimitive("asec", new Atom(new Asec()));
        env.setPrimitive("acot", new Atom(new Acot()));
        env.setPrimitive("sinh", new Atom(new Sinh()));
        env.setPrimitive("cosh", new Atom(new Cosh()));
        env.setPrimitive("tanh", new Atom(new Tanh()));
        env.setPrimitive("coth", new Atom(new Coth()));
        env.setPrimitive("sech", new Atom(new Sech()));
        env.setPrimitive("csch", new Atom(new Csch()));
        env.setPrimitive("asinh", new Atom(new Asinh()));
        env.setPrimitive("acosh", new Atom(new Acosh()));
        env.setPrimitive("atanh", new Atom(new Atanh()));
        env.setPrimitive("acoth", new Atom(new Acoth()));
        env.setPrimitive("asech", new Atom(new Asech()));
        env.setPrimitive("acsch", new Atom(new Acsch()));
        env.setPrimitive("log2", new Atom(new Log2()));
        env.setPrimitive("log10", new Atom(new Log10()));
        env.setPrimitive("gcd", new Atom(new Gcd()));
        env.setPrimitive("lcm", new Atom(new Lcm()));
        env.setPrimitive("gamma", "Γ", new Atom(new Gamma()));
        env.setPrimitive("not", "¬", new Atom(new Not()));
        env.setPrimitive("fib", new Atom(new Fib()));
        env.setPrimitive("ceil", "⌈", new Atom(new Ceil()));
        env.setPrimitive("floor", "⌊", new Atom(new Floor()));
        env.setPrimitive("round", new Atom(new Round()));
        env.setPrimitive("and", "∧", new Atom(new And()));
        env.setPrimitive("or", "∨", new Atom(new Or()));
        env.setPrimitive("exp", new Atom(new Exp()));
        env.setPrimitive("max", new Atom(new Max()));
        env.setPrimitive("min", new Atom(new Min()));
        env.setPrimitive("signum", new Atom(new Signum()));
        env.setPrimitive("true", "⊤", Atom.TRUE);
        env.setPrimitive("false", "⊥", Atom.FALSE);
        env.setPrimitive("=", new Atom(new Eq()));
        env.setPrimitive("/=", "≠", new Atom(new Neq()));
        env.setPrimitive("pi", "π", new Atom(new Pi()));
        env.setPrimitive("e", new Atom(new E()));
        env.setPrimitive("+", new Atom(new Plus()));
        env.setPrimitive("-", new Atom(new Minus()));
        env.setPrimitive("*", new Atom(new Star()));
        env.setPrimitive("/", new Atom(new Slash()));
        env.setPrimitive("mod", new Atom(new Mod()));
        env.setPrimitive("<=>", "‡", new Atom(new Spaceship()));
        env.setPrimitive("ucs", new Atom(new Ucs()));
        env.setPrimitive("ucb", new Atom(new Ucb()));
        env.setPrimitive("match", "→", new Atom(new Match()));
        env.setPrimitive("random", "⍰", new Atom(new Random()));
        env.setPrimitive("fft", "⊙↖⍠", new Atom(new FFT()));
        env.setPrimitive("ifft", "⊙↘⍠", new Atom(new IFFT()));
    }
}