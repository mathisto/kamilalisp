package palaiologos.kamilalisp.runtime.lib;

import palaiologos.kamilalisp.atom.Atom;
import palaiologos.kamilalisp.atom.Environment;
import palaiologos.kamilalisp.runtime.IO.Readln;
import palaiologos.kamilalisp.runtime.IO.Write;
import palaiologos.kamilalisp.runtime.IO.Writeln;
import palaiologos.kamilalisp.runtime.array.*;
import palaiologos.kamilalisp.runtime.array.carcdr.*;
import palaiologos.kamilalisp.runtime.array.hof.*;
import palaiologos.kamilalisp.runtime.math.FromDigits;
import palaiologos.kamilalisp.runtime.math.ToDigits;
import palaiologos.kamilalisp.runtime.math.Transpose;
import palaiologos.kamilalisp.runtime.math.prime.IsPrime;
import palaiologos.kamilalisp.runtime.math.prime.NextPrime;
import palaiologos.kamilalisp.runtime.math.prime.PrimeFactors;
import palaiologos.kamilalisp.runtime.math.prime.PrimeNo;
import palaiologos.kamilalisp.runtime.meta.*;
import palaiologos.kamilalisp.runtime.string.*;

import java.math.BigDecimal;

public class BaseLib {
    public static void register(Environment env) {
        env.setPrimitive("fr", new Atom(new BigDecimal(100)));
        env.setPrimitive("lambda", "λ", new Atom(new Dfn()));
        env.setPrimitive("def", "○←", new Atom(new GlobalBinding()));
        env.setPrimitive("car", "⍎", new Atom(Car.INSTANCE));
        env.setPrimitive("cdr", "⍕", new Atom(Cdr.INSTANCE));
        env.setPrimitive("caar", "⍎⍎", new Atom(new Caar()));
        env.setPrimitive("cadr", "⍎⍕", new Atom(new Cadr()));
        env.setPrimitive("cdar", "⍕⍎", new Atom(new Cdar()));
        env.setPrimitive("cddr", "⍕⍕", new Atom(new Cddr()));
        env.setPrimitive("reverse", "⌽", new Atom(new Reverse()));
        env.setPrimitive("rotate", "⊖", new Atom(new Rotate()));
        env.setPrimitive("range", "⍳", new Atom(new Range()));
        env.setPrimitive("foldl", "⌿←", new Atom(new Foldl()));
        env.setPrimitive("foldr", "⌿→", new Atom(new Foldr()));
        env.setPrimitive("foldl1", "⌿⊙←", new Atom(new Foldl1()));
        env.setPrimitive("foldr1", "⌿⊙→", new Atom(new Foldr1()));
        env.setPrimitive("lift", "⍏", new Atom(new Lift()));
        env.setPrimitive("tie", "⌿⊙⍧", new Atom(new Tie()));
        env.setPrimitive("if", "↕", new Atom(new If()));
        env.setPrimitive("filter", "⍭", new Atom(new Filter()));
        env.setPrimitive("filter-idx", "⍭¨", new Atom(new FilterIdx()));
        env.setPrimitive("map-idx", "⍠¨", new Atom(new MapIdx()));
        env.setPrimitive("any", "∨?", new Atom(new Any()));
        env.setPrimitive("all", "∧?", new Atom(new All()));
        env.setPrimitive("none", "∅?", new Atom(new None()));
        env.setPrimitive("decode", "⊥⍟", new Atom(new Decode()));
        env.setPrimitive("encode", "⊤⍟", new Atom(new Encode()));
        env.setPrimitive("count", "⍴?", new Atom(new Count()));
        env.setPrimitive("sort-asc", "⊼", new Atom(new Sort()));
        env.setPrimitive("sort-desc", "⊻", new Atom(new SortDesc()));
        env.setPrimitive("scanl", "⍀←", new Atom(new Scanl()));
        env.setPrimitive("scanl1", "⍀⊙←", new Atom(new Scanl1()));
        env.setPrimitive("scanr", "⍀→", new Atom(new Scanr()));
        env.setPrimitive("scanr1", "⍀⊙→", new Atom(new Scanr1()));
        env.setPrimitive("bsearch", "⊃∊", new Atom(new Bsearch()));
        env.setPrimitive("replicate", "∥", new Atom(new Replicate()));
        env.setPrimitive("defun", "⍥←", new Atom(new Defun()));
        env.setPrimitive("outer-product", "⌼", new Atom(new OuterProduct()));
        env.setPrimitive("inner-product", "⌻", new Atom(new InnerProduct()));
        env.setPrimitive("shannon-entropy", "⍫⍴", new Atom(new Shannon()));
        env.setPrimitive("tally", "⍴", new Atom(new Tally()));
        env.setPrimitive("rank", "⍴⍴", new Atom(new Rank()));
        env.setPrimitive("same", "≡", new Atom(new Same()));
        env.setPrimitive("converge", "→≡", new Atom(new Converge()));
        env.setPrimitive("not-same", "≢", new Atom(new NotSame()));
        env.setPrimitive("same-elements", "⍉≡", new Atom(new SameElements()));
        env.setPrimitive("not-same-elements", "⍉≢", new Atom(new NotSameElements()));
        env.setPrimitive("grade-up", "⍋", new Atom(new GradeUp()));
        env.setPrimitive("grade-down", "⍒", new Atom(new GradeDown()));
        env.setPrimitive("cons", "⍟", new Atom(new Cons()));
        env.setPrimitive("pmat", "⍉↩⍳", new Atom(new Pmat()));
        env.setPrimitive("flatten", "∊", new Atom(new Flatten()));
        env.setPrimitive("let", "○⊢", new Atom(new Let()));
        env.setPrimitive("let*", "○⊢*", new Atom(new LetStar()));
        env.setPrimitive("cond", "↕¨", new Atom(new Cond()));
        env.setPrimitive("append", "⍠", new Atom(new Append()));
        env.setPrimitive("group", "⌸", new Atom(new Group()));
        env.setPrimitive("try-catch", "‼", new Atom(new TryCatch()));
        env.setPrimitive("raise", "↑‼", new Atom(new Raise()));
        env.setPrimitive("let-seq", "○⊢¨", new Atom(new LetSeq()));
        env.setPrimitive("while", "⍣", new Atom(new While()));
        env.setPrimitive("partial-while", "⍀⍣", new Atom(new PartialWhile()));
        env.setPrimitive("memo", "⌹↔", new Atom(new Memo()));
        env.setPrimitive("index-of", "∊?", new Atom(new IndexOf()));
        env.setPrimitive("to-digits", "⌹⊙", new Atom(new ToDigits()));
        env.setPrimitive("without", "⍪", new Atom(new Without()));
        env.setPrimitive("from-digits", "⊙⌹", new Atom(new FromDigits()));
        env.setPrimitive("cycle", "⍉↩", new Atom(new Cycle()));
        env.setPrimitive("take", "↑", new Atom(new Take()));
        env.setPrimitive("drop", "↓", new Atom(new Drop()));
        env.setPrimitive("in?", "⍠∊?", new Atom(new In()));
        env.setPrimitive("unique-mask", "⊙¨", new Atom(new UniqueMask()));
        env.setPrimitive("unique", "⊙", new Atom(new Unique()));
        env.setPrimitive("intersection", "∩", new Atom(new Intersection()));
        env.setPrimitive("union", "∪", new Atom(new Union()));
        env.setPrimitive("parse-number", "⊙⍫", new Atom(new ParseNumber()));
        env.setPrimitive("prefixes", "ᑈ", new Atom(new Prefixes()));
        env.setPrimitive("suffixes", "ᐵ", new Atom(new Suffixes()));
        env.setPrimitive("interleave", "⍧", new Atom(new Interleave()));
        env.setPrimitive("take-while", "⍣↑", new Atom(new TakeWhile()));
        env.setPrimitive("drop-while", "⍣↓", new Atom(new DropWhile()));
        env.setPrimitive("find", "⍷", new Atom(new Find()));
        env.setPrimitive("find-idx", "⍸⍷", new Atom(new FindIdx()));
        env.setPrimitive("where", "⍸", new Atom(new Where()));
        env.setPrimitive("where-mask", "⍸¯¹", new Atom(new WhereMask()));
        env.setPrimitive("powerset", "⍉⍉", new Atom(new Powerset()));
        env.setPrimitive("discard", "∅←", new Atom(new Discard()));
        env.setPrimitive("empty?", "⍠⍉?", new Atom(new IsNil()));
        env.setPrimitive("starts-with", "⍠⊂←", new Atom(new StartsWith()));
        env.setPrimitive("insert", "⍠⊣", new Atom(new Insert()));
        env.setPrimitive("insert-all", "⍠⊣∵", new Atom(new InsertAll()));
        env.setPrimitive("shuffle", "⍠⍰", new Atom(new Shuffle()));
        env.setPrimitive("bipartition", "⍡¨", new Atom(new Bipartition()));
        env.setPrimitive("partition", "⍡", new Atom(new Partition()));
        env.setPrimitive("partition-index", "⍡⍸", new Atom(new PartitionIndex()));
        env.setPrimitive("slice", "↑-↓", new Atom(new Slice()));
        env.setPrimitive("windows", "⌹‡⍡", new Atom(new Windows()));
        env.setPrimitive("transpose", "⎕⍉", new Atom(new Transpose()));

        env.setPrimitive("prime:factors", "⋔⌹", new Atom(new PrimeFactors()));
        env.setPrimitive("prime:is?", "⋔?", new Atom(new IsPrime()));
        env.setPrimitive("prime:next", "⋔↑", new Atom(new NextPrime()));
        env.setPrimitive("prime:nth", "⋔→", new Atom(new PrimeNo()));

        env.setPrimitive("str:format", "⍫∊", new Atom(new Format()));
        env.setPrimitive("str:lines", "⍫↩", new Atom(new Lines()));
        env.setPrimitive("str:implode", "⍫∨", new Atom(new Implode()));
        env.setPrimitive("str:lexer", "⍫∦", new Atom(new Lexer()));
        env.setPrimitive("str:implode-on", "⍫∨¨", new Atom(new ImplodeOn()));
        env.setPrimitive("str:explode", "⍫∧", new Atom(new Explode()));
        env.setPrimitive("str:upper", new Atom(new Upper()));
        env.setPrimitive("str:lower", new Atom(new Lower()));
        env.setPrimitive("to-string", "⍫←", new Atom(new ToString()));
        env.setPrimitive("str:escape", "⍫⑊", new Atom(new Escape()));
        env.setPrimitive("str:unescape", "⍫∼⑊", new Atom(new Unescape()));
        env.setPrimitive("str:contains", "⍫⊂←", new Atom(new Contains()));
        env.setPrimitive("str:levenshtein", "⍫≉", new Atom(new Levenshtein()));
        env.setPrimitive("str:wrap", "⍫⑊↩", new Atom(new Wrap()));

        env.setPrimitive("io:writeln", "↑⍫", new Atom(new Writeln()));
        env.setPrimitive("io:readln", "↓⍫", new Atom(new Readln()));
        env.setPrimitive("io:write", "↗⍫", new Atom(new Write()));
    }
}
