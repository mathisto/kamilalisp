package palaiologos.kamilalisp.runtime.meta;

import palaiologos.kamilalisp.atom.*;
import palaiologos.kamilalisp.runtime.Dfn;

import java.util.List;

public class LetSeq extends PrimitiveFunction implements SpecialForm {

    @Override
    public Atom apply(Environment env, List<Atom> args) {
        Atom lastValue = Atom.NULL;
        for (Atom arg : args) {
            if (arg.getType() == Type.LIST && !arg.getList().isEmpty()) {
                if (arg.getList().get(0).getType() == Type.IDENTIFIER) {
                    switch (Identifier.of(arg.getList().get(0).getIdentifier())) {
                        case "⍥←":
                        case "defun": {
                            List<Atom> declaration = arg.getList();
                            if (declaration.get(1).getType() != Type.IDENTIFIER) {
                                throw new RuntimeException("Expected identifier in `defun' declaration in `let-seq'.");
                            }
                            String name = Identifier.of(declaration.get(1).getIdentifier());
                            Atom lambdaArgs = declaration.get(2);
                            Atom lambdaValue = declaration.get(3);
                            Atom lambda = Evaluation.evaluate(env, new Dfn(), List.of(lambdaArgs, lambdaValue));
                            env.set(name, lambda);
                            continue;
                        }
                        case "○←":
                        case "def": {
                            List<Atom> declaration = arg.getList();
                            if (declaration.get(1).getType() != Type.IDENTIFIER) {
                                throw new RuntimeException("Expected identifier in `def' declaration in `let-seq'.");
                            }
                            String name = Identifier.of(declaration.get(1).getIdentifier());
                            Atom value = Evaluation.evaluate(env, declaration.get(2));
                            env.set(name, value);
                            continue;
                        }
                        case "↩":
                        case "case": {
                            // Yield if true.
                            List<Atom> declaration = arg.getList();
                            Atom condition = declaration.get(1);
                            Atom value = declaration.get(2);
                            if (Evaluation.evaluate(env, condition).coerceBool()) {
                                return Evaluation.evaluate(env, value);
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }

            lastValue = Evaluation.evaluate(env, arg);
        }

        return lastValue;
    }

    @Override
    protected String name() {
        return "let-seq";
    }
}
