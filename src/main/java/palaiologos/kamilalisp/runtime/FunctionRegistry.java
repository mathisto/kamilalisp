package palaiologos.kamilalisp.runtime;

import palaiologos.kamilalisp.atom.Atom;
import palaiologos.kamilalisp.atom.Environment;
import palaiologos.kamilalisp.runtime.IO.*;
import palaiologos.kamilalisp.runtime.IO.streams.*;
import palaiologos.kamilalisp.runtime.array.*;
import palaiologos.kamilalisp.runtime.array.carcdr.*;
import palaiologos.kamilalisp.runtime.array.hof.*;
import palaiologos.kamilalisp.runtime.array.sais.SacaBwt;
import palaiologos.kamilalisp.runtime.array.sais.SacaSais;
import palaiologos.kamilalisp.runtime.array.sais.SacaUnbwt;
import palaiologos.kamilalisp.runtime.cas.*;
import palaiologos.kamilalisp.runtime.dataformat.*;
import palaiologos.kamilalisp.runtime.dataformat.archive.*;
import palaiologos.kamilalisp.runtime.dataformat.digest.*;
import palaiologos.kamilalisp.runtime.datetime.*;
import palaiologos.kamilalisp.runtime.flt64.Flt64Base;
import palaiologos.kamilalisp.runtime.hashmap.*;
import palaiologos.kamilalisp.runtime.image.LoadImage;
import palaiologos.kamilalisp.runtime.image.WriteImage;
import palaiologos.kamilalisp.runtime.math.*;
import palaiologos.kamilalisp.runtime.math.bit.*;
import palaiologos.kamilalisp.runtime.math.cmp.*;
import palaiologos.kamilalisp.runtime.math.hyperbolic.*;
import palaiologos.kamilalisp.runtime.math.num.*;
import palaiologos.kamilalisp.runtime.math.prime.IsPrime;
import palaiologos.kamilalisp.runtime.math.prime.NextPrime;
import palaiologos.kamilalisp.runtime.math.prime.PrimeFactors;
import palaiologos.kamilalisp.runtime.math.prime.PrimeNo;
import palaiologos.kamilalisp.runtime.math.trig.*;
import palaiologos.kamilalisp.runtime.meta.*;
import palaiologos.kamilalisp.runtime.net.*;
import palaiologos.kamilalisp.runtime.parallel.Daemon;
import palaiologos.kamilalisp.runtime.parallel.Task;
import palaiologos.kamilalisp.runtime.parallel.actor.ActorCreate;
import palaiologos.kamilalisp.runtime.regex.RegexMatches;
import palaiologos.kamilalisp.runtime.regex.RegexReplace;
import palaiologos.kamilalisp.runtime.regex.RegexSplit;
import palaiologos.kamilalisp.runtime.sh.Process;
import palaiologos.kamilalisp.runtime.sh.*;
import palaiologos.kamilalisp.runtime.string.*;

import java.math.BigDecimal;

public class FunctionRegistry {
    public static void registerDefault(Environment env) {
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
        env.setPrimitive("foldl1", "⌿.←", new Atom(new Foldl1()));
        env.setPrimitive("foldr1", "⌿.→", new Atom(new Foldr1()));
        env.setPrimitive("lift", "⍏", new Atom(new Lift()));
        env.setPrimitive("tie", "⌿.⍧", new Atom(new Tie()));
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
        env.setPrimitive("scanl1", "⍀.←", new Atom(new Scanl1()));
        env.setPrimitive("scanr", "⍀→", new Atom(new Scanr()));
        env.setPrimitive("scanr1", "⍀.→", new Atom(new Scanr1()));
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
        env.setPrimitive("cmpx", new Atom(new Cmpx()));
        env.setPrimitive("import", "○←⍫", new Atom(new Import()));
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
        env.setPrimitive("intersection", "⋂", new Atom(new Intersection()));
        env.setPrimitive("union", "⋃", new Atom(new Union()));
        env.setPrimitive("parse-number", "⊙⍫", new Atom(new ParseNumber()));
        env.setPrimitive("prefixes", "ᑈ", new Atom(new Prefixes()));
        env.setPrimitive("suffixes", "ᐵ", new Atom(new Suffixes()));
        env.setPrimitive("interleave", "⍧", new Atom(new Interleave()));
        env.setPrimitive("take-while", "⍣↑", new Atom(new TakeWhile()));
        env.setPrimitive("drop-while", "⍣↓", new Atom(new DropWhile()));
        env.setPrimitive("find", "⍷", new Atom(new Find()));
        env.setPrimitive("find-idx", "⍸⍷", new Atom(new FindIdx()));
        env.setPrimitive("where", "⍸", new Atom(new Where()));
        env.setPrimitive("powerset", "⍉⍉", new Atom(new Powerset()));
        env.setPrimitive("discard", "∅←", new Atom(new Discard()));
        env.setPrimitive("empty?", "⍠⍉?", new Atom(new IsNil()));
        env.setPrimitive("starts-with", "⍠⊂←", new Atom(new StartsWith()));
        env.setPrimitive("insert", "⍠⊣", new Atom(new Insert()));
        env.setPrimitive("insert-all", "⍠⊣∵", new Atom(new InsertAll()));
        env.setPrimitive("exit", "→⋄", new Atom(new Exit()));
        env.setPrimitive("shuffle", "⍠⍰", new Atom(new Shuffle()));
        env.setPrimitive("bipartition", "⍡¨", new Atom(new Bipartition()));
        env.setPrimitive("partition", "⍡", new Atom(new PartitionRange()));
        env.setPrimitive("windows", "⌹‡⍡", new Atom(new Windows()));
        env.setPrimitive("transpose", "⎕⍉", new Atom(new Transpose()));

        env.setPrimitive("img:write", "≣⊣", new Atom(new WriteImage()));
        env.setPrimitive("img:read", "≣⊢", new Atom(new LoadImage()));

        env.setPrimitive("prime:factors", "⋔⌹", new Atom(new PrimeFactors()));
        env.setPrimitive("prime:is?", "⋔?", new Atom(new IsPrime()));
        env.setPrimitive("prime:next", "⋔↑", new Atom(new NextPrime()));
        env.setPrimitive("prime:nth", "⋔→", new Atom(new PrimeNo()));

        env.setPrimitive("meta:env-keys", "⊚⍠", new Atom(new EnvKeys()));
        env.setPrimitive("meta:to-glyphs", "⊚∊", new Atom(new ToGlyphs()));
        env.setPrimitive("meta:to-ascii", "⊚∉", new Atom(new ToASCII()));
        env.setPrimitive("meta:type-name", "⊚?", new Atom(new TypeName()));
        env.setPrimitive("meta:atom", "→⊚", new Atom(new MetaAtom()));

        env.setPrimitive("str:format", "⍫∊", new Atom(new Format()));
        env.setPrimitive("str:lines", "⍫↩", new Atom(new Lines()));
        env.setPrimitive("str:implode", "⍫∨", new Atom(new Implode()));
        env.setPrimitive("str:lexer", "⍫∦", new Atom(new Lexer()));
        env.setPrimitive("str:implode-on", "⍫∨¨", new Atom(new ImplodeOn()));
        env.setPrimitive("str:explode", "⍫∧", new Atom(new Explode()));
        env.setPrimitive("to-string", "⍫←", new Atom(new ToString()));
        env.setPrimitive("str:escape", "⍫⑊", new Atom(new Escape()));
        env.setPrimitive("str:unescape", "⍫∼⑊", new Atom(new Unescape()));
        env.setPrimitive("str:contains", "⍫⊂←", new Atom(new Contains()));
        env.setPrimitive("str:levenshtein", "⍫≉", new Atom(new Levenshtein()));
        env.setPrimitive("str:wrap", "⍫⑊↩", new Atom(new Wrap()));

        env.setPrimitive("parallel:task", "∥⎕", new Atom(new Task()));
        env.setPrimitive("parallel:daemon", "∥⍌", new Atom(new Daemon()));
        env.setPrimitive("parallel:actor", "∥⍓", new Atom(new ActorCreate()));
        env.setPrimitive("parallel:map-idx", "⍠∵", new Atom(new ParallelMapIdx()));
        env.setPrimitive("parallel:filter", "⍭∵", new Atom(new ParallelFilter()));

        env.setPrimitive("io:writeln", "↑⍫", new Atom(new Writeln()));
        env.setPrimitive("io:readln", "↓⍫", new Atom(new Readln()));
        env.setPrimitive("io:write", "↗⍫", new Atom(new Write()));
        env.setPrimitive("io:get-file", "⍫⊢", new Atom(new GetFile()));
        env.setPrimitive("io:put-file", "⍫⊣", new Atom(new PutFile()));
        env.setPrimitive("io:append-file", "⍫⊣+", new Atom(new AppendFile()));
        env.setPrimitive("io:get-file-buffer", "⍫⎕⊢", new Atom(new GetFileBuffer()));
        env.setPrimitive("io:file-istream", new Atom(new FileInputStreamWrapper()));
        env.setPrimitive("io:file-ostream", new Atom(new FileOutputStreamWrapper()));
        env.setPrimitive("io:histogram-istream", new Atom(new HistogramInputStream()));
        env.setPrimitive("io:histogram-ostream", new Atom(new HistogramOutputStream()));
        env.setPrimitive("io:byte-buffer-istream", new Atom(new ByteBufferInputStream()));
        env.setPrimitive("io:byte-buffer-ostream", new Atom(new ByteBufferOutputStream()));
        env.setPrimitive("io:gzip-istream", new Atom(new GzipInputStream()));
        env.setPrimitive("io:gzip-ostream", new Atom(new GzipOutputStream()));
        env.setPrimitive("io:bzip2-istream", new Atom(new Bzip2InputStream()));
        env.setPrimitive("io:bzip2-ostream", new Atom(new Bzip2OutputStream()));
        env.setPrimitive("io:lz4-istream", new Atom(new Lz4InputStream()));
        env.setPrimitive("io:lz4-ostream", new Atom(new Lz4OutputStream()));
        env.setPrimitive("io:xz-istream", new Atom(new XzInputStream()));
        env.setPrimitive("io:xz-ostream", new Atom(new XzOutputStream()));
        env.setPrimitive("io:input-stream-of", new Atom(new InputStreamOf()));
        env.setPrimitive("io:output-stream-of", new Atom(new OutputStreamOf()));
        env.setPrimitive("io:tee-ostream", new Atom(new TeeOutputStream()));
        env.setPrimitive("io:null-istream", new Atom(new NullInputStream()));
        env.setPrimitive("io:null-ostream", new Atom(new NullOutputStream()));
        env.setPrimitive("io:md5-istream", new Atom(new Md5DigestIOStreams.Input()));
        env.setPrimitive("io:md5-ostream", new Atom(new Md5DigestIOStreams.Output()));
        env.setPrimitive("io:md2-istream", new Atom(new Md2DigestIOStreams.Input()));
        env.setPrimitive("io:md2-ostream", new Atom(new Md2DigestIOStreams.Output()));
        env.setPrimitive("io:adler32-istream", new Atom(new Adler32DigestIOStreams.Input()));
        env.setPrimitive("io:adler32-ostream", new Atom(new Adler32DigestIOStreams.Output()));
        env.setPrimitive("io:crc32-ostream", new Atom(new CRC32DigestIOStreams.Output()));
        env.setPrimitive("io:crc32-istream", new Atom(new CRC32DigestIOStreams.Input()));
        env.setPrimitive("io:crc32c-ostream", new Atom(new CRC32CDigestIOStreams.Output()));
        env.setPrimitive("io:crc32c-istream", new Atom(new CRC32CDigestIOStreams.Input()));
        env.setPrimitive("io:sha1-istream", new Atom(new Sha1DigestIOStreams.Input()));
        env.setPrimitive("io:sha1-ostream", new Atom(new Sha1DigestIOStreams.Output()));
        env.setPrimitive("io:xxh32-istream", new Atom(new Xxh32DigestIOStreams.Input()));
        env.setPrimitive("io:xxh32-ostream", new Atom(new Xxh32DigestIOStreams.Output()));
        env.setPrimitive("io:audio-ostream", new Atom(new AudioOutputStream()));
        env.setPrimitive("io:encode-le-u16", new Atom(new EncodeLE16()));
        env.setPrimitive("io:encode-be-u16", new Atom(new EncodeBE16()));
        env.setPrimitive("io:encode-le-u32", new Atom(new EncodeLE32()));
        env.setPrimitive("io:encode-be-u32", new Atom(new EncodeBE32()));
        env.setPrimitive("io:encode-le-u64", new Atom(new EncodeLE64()));
        env.setPrimitive("io:encode-be-u64", new Atom(new EncodeBE64()));
        env.setPrimitive("io:encode-le-s16", new Atom(new EncodeLES16()));
        env.setPrimitive("io:encode-be-s16", new Atom(new EncodeBES16()));
        env.setPrimitive("io:encode-le-s32", new Atom(new EncodeLES32()));
        env.setPrimitive("io:encode-be-s32", new Atom(new EncodeBES32()));
        env.setPrimitive("io:encode-le-s64", new Atom(new EncodeLES64()));
        env.setPrimitive("io:encode-be-s64", new Atom(new EncodeBES64()));
        env.setPrimitive("io:decode-le", new Atom(new DecodeLE()));
        env.setPrimitive("io:decode-be", new Atom(new DecodeBE()));

        env.setPrimitive("digest:md2", new Atom(new Md2Digest()));
        env.setPrimitive("digest:md5", new Atom(new Md5Digest()));
        env.setPrimitive("digest:sha1", new Atom(new Sha1Digest()));
        env.setPrimitive("digest:sha256", new Atom(new Sha256Digest()));
        env.setPrimitive("digest:sha384", new Atom(new Sha384Digest()));
        env.setPrimitive("digest:sha512", new Atom(new Sha512Digest()));
        env.setPrimitive("digest:xxh32", new Atom(new XXHash32Digest()));
        env.setPrimitive("digest:crc32", new Atom(new CRC32Digest()));
        env.setPrimitive("digest:crc32c", new Atom(new CRC32CDigest()));
        env.setPrimitive("digest:adler32", new Atom(new Adler32Digest()));
        env.setPrimitive("digest:sha3224", new Atom(new Sha3224Digest()));
        env.setPrimitive("digest:sha3256", new Atom(new Sha3256Digest()));
        env.setPrimitive("digest:sha3384", new Atom(new Sha3384Digest()));
        env.setPrimitive("digest:sha3512", new Atom(new Sha3512Digest()));
        env.setPrimitive("digest:sha512224", new Atom(new Sha512224Digest()));
        env.setPrimitive("digest:sha512256", new Atom(new Sha512256Digest()));

        env.setPrimitive("saca:sais", new Atom(new SacaSais()));
        env.setPrimitive("saca:bwt", new Atom(new SacaBwt()));
        env.setPrimitive("saca:unbwt", new Atom(new SacaUnbwt()));

        env.setPrimitive("num:LU", "⎕↙↗", new Atom(new LUDecomposition()));
        env.setPrimitive("num:PLU", "⎕⍉↙↗", new Atom(new PLUDecomposition()));
        env.setPrimitive("num:trace", "⎕∑", new Atom(new Trace()));
        env.setPrimitive("num:det", "⎕∆", new Atom(new Det()));
        env.setPrimitive("num:permanent", "⎕∆⍴", new Atom(new Permanent()));
        env.setPrimitive("num:I", "⎕I", new Atom(new I()));
        env.setPrimitive("num:invert", "⎕¯¹", new Atom(new Inv()));

        env.setPrimitive("cas:matrix:trace", "⎕ƒ∑", new Atom(new MatrixTrace()));
        env.setPrimitive("cas:matrix:LU", "⎕ƒ↙↗", new Atom(new MatrixLUDecomposition()));
        env.setPrimitive("cas:matrix:det", "⎕ƒ∆", new Atom(new MatrixDet()));

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

        new Flt64Base().registerFlt64(env);

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

        env.setPrimitive("regex:matches?", "⍫⊖∊?", new Atom(new RegexMatches()));
        env.setPrimitive("regex:replace", "⍫⊖⍆", new Atom(new RegexReplace()));
        env.setPrimitive("regex:split", "⍫⊖⍭", new Atom(new RegexSplit()));

        env.setPrimitive("xml:parse", new Atom(new XmlParse()));
        env.setPrimitive("xml:write", new Atom(new XmlWrite()));
        env.setPrimitive("xml:escape", new Atom(new XmlEscape()));
        env.setPrimitive("xml:unescape", new Atom(new XmlUnescape()));
        env.setPrimitive("csv:parse", new Atom(new CsvParse()));
        env.setPrimitive("csv:write", new Atom(new CsvWrite()));
        env.setPrimitive("json:parse", new Atom(new JsonParse()));
        env.setPrimitive("json:write", new Atom(new JsonWrite()));

        env.setPrimitive("codec:gzip-compress", new Atom(new GzipCompress()));
        env.setPrimitive("codec:gzip-decompress", new Atom(new GzipDecompress()));
        env.setPrimitive("codec:bzip2-compress", new Atom(new Bzip2Compress()));
        env.setPrimitive("codec:bzip2-decompress", new Atom(new Bzip2Decompress()));
        env.setPrimitive("codec:lz4-compress", new Atom(new Lz4Compress()));
        env.setPrimitive("codec:lz4-decompress", new Atom(new Lz4Decompress()));
        env.setPrimitive("codec:xz-compress", new Atom(new XzCompress()));
        env.setPrimitive("codec:xz-decompress", new Atom(new XzDecompress()));
        env.setPrimitive("codec:base64-encode", new Atom(new Base64Encode()));
        env.setPrimitive("codec:base64-decode", new Atom(new Base64Decode()));

        env.setPrimitive("archive:zip:create", new Atom(new ZipCreate()));
        env.setPrimitive("archive:zip:load", new Atom(new ZipLoad()));
        env.setPrimitive("archive:zip:save", new Atom(new ZipSave()));
        env.setPrimitive("archive:tar:create", new Atom(new TarCreate()));
        env.setPrimitive("archive:tar:load", new Atom(new TarLoad()));
        env.setPrimitive("archive:tar:save", new Atom(new TarSave()));

        env.setPrimitive("sh:mkdir", new Atom(new Mkdir()));
        env.setPrimitive("sh:cwd", new Atom(new Cwd()));
        env.setPrimitive("sh:cd", new Atom(new Cd()));
        env.setPrimitive("sh:rm", new Atom(new Rm()));
        env.setPrimitive("sh:glob", new Atom(new Glob()));
        env.setPrimitive("sh:cp", new Atom(new Cp()));
        env.setPrimitive("sh:ls", new Atom(new Ls()));
        env.setPrimitive("sh:basename", new Atom(new Basename()));
        env.setPrimitive("sh:mv", new Atom(new Mv()));
        env.setPrimitive("sh:access", new Atom(new Access()));
        env.setPrimitive("sh:absolute", new Atom(new Absolute()));
        env.setPrimitive("sh:process", new Atom(new Process()));
        SysArch.registerProperties(env);

        env.setPrimitive("date:from", new Atom(new DateTimeFrom()));
        env.setPrimitive("time:from", new Atom(new TimeFrom()));
        env.setPrimitive("time:hours", new Atom(new TimeHours()));
        env.setPrimitive("time:minutes", new Atom(new TimeMinutes()));
        env.setPrimitive("time:seconds", new Atom(new TimeSeconds()));
        env.setPrimitive("time:nanoseconds", new Atom(new TimeNanoseconds()));
        env.setPrimitive("date:years", new Atom(new DateYears()));
        env.setPrimitive("date:months", new Atom(new DateMonths()));
        env.setPrimitive("date:days", new Atom(new DateDays()));
        env.setPrimitive("date:now-utc", new Atom(new DateNow()));
        env.setPrimitive("date:now", new Atom(new DateNowTZ()));
        env.setPrimitive("time:now-utc", new Atom(new TimeNow()));
        env.setPrimitive("time:now", new Atom(new TimeNowTZ()));
        env.setPrimitive("date:parse", new Atom(new DateParse()));
        env.setPrimitive("time:parse", new Atom(new TimeParse()));

        env.setPrimitive("net:wget", new Atom(new Wget()));
        env.setPrimitive("net:fetch", new Atom(new Fetch()));
        env.setPrimitive("net:client", new Atom(new NetClient()));
        env.setPrimitive("net:client-ssl", new Atom(new NetClientSSL()));
        env.setPrimitive("net:server", new Atom(new NetServer()));
        env.setPrimitive("net:server-ssl", new Atom(new NetServerSSL()));
        env.setPrimitive("net:http-server", new Atom(new HTTPServer()));

        env.setPrimitive("hashmap:from-list", "⍔⌿", new Atom(new HashMapFromList()));
        env.setPrimitive("hashmap:as-list", "⍔⍀", new Atom(new HashMapAsList()));
        env.setPrimitive("hashmap:size", "⍔⍴", new Atom(new HashMapSize()));
        env.setPrimitive("hashmap:key-list", "⍔⍎", new Atom(new HashMapKeyList()));
        env.setPrimitive("hashmap:value-list", "⍔⍕", new Atom(new HashMapValueList()));
        env.setPrimitive("hashmap:contains-key?", "⍔⍎?", new Atom(new HashMapContainsKey()));
        env.setPrimitive("hashmap:contains-value?", "⍔⍕?", new Atom(new HashMapContainsValue()));
        env.setPrimitive("hashmap:get", "⍔⍆", new Atom(new HashMapGet()));
        env.setPrimitive("hashmap:get-or", "⍔⍆?", new Atom(new HashMapGetOrDefault()));
        env.setPrimitive("hashmap:adjoin", "⍔+", new Atom(new HashMapAdjoin()));
        env.setPrimitive("hashmap:minus", "⍔-", new Atom(new HashMapMinus()));
        env.setPrimitive("hashmap:merge", "⍔⋃", new Atom(new HashMapMerge()));
        env.setPrimitive("hashmap:without", "⍔⍪", new Atom(new HashMapWithout()));
        env.setPrimitive("hashmap:group", "⍔⌸", new Atom(new HashMapGroup()));
        env.setPrimitive("hashmap:process", "⍔∵", new Atom(new HashMapProcess()));
    }
}
