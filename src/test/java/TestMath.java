import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
class TestMath {
    @Test
    void testMath() {
        assertDoesNotThrow(() -> Common.runCode("""
                (let-seq
                    (def fr 8)
                    (def a (* 2 3))
                    (if (/= a 6) (raise "a is not 6") 'nil)
                    (def b (+ 2 3))
                    (if (/= b 5) (raise "b is not 5") 'nil)
                    (def c (- 2 3))
                    (if (/= c -1) (raise "c is not -1") 'nil)
                    (def d (/ 2 3))
                    (if (/= d 0.66666667) (raise \\str:format "d is not 0.66666667, got {?d}") 'nil)
                    (def ee (** 2 3))
                    (if (/= ee 8) (raise "ee is not 8") 'nil)
                    (def f (** 4 0.5))
                    (if (/= f 2) (raise "f is not 2") 'nil)
                    (def g (** 2 -1))
                    (if (/= g 0.5) (raise "g is not 0.5") 'nil)
                    (def h (** 2 0))
                    (if (/= h 1) (raise "h is not 1") 'nil)
                    (def i (** 2 1))
                    (if (/= i 2) (raise "i is not 2") 'nil)
                    (def j (pi 2))
                    (if (/= j 6.2831854) (raise \\str:format "j is not 6.2831854, got {?j}") 'nil)
                    (def k (e 2))
                    (if (/= k 5.4365636) (raise \\str:format "k is not 5.4365636, got {?k}") 'nil)
                    (def l (bit:and 151 37))
                    (if (/= l 5) (raise \\str:format "l is not 5, got {?l}") 'nil)
                    (def m (bit:or 132 67))
                    (if (/= m 199) (raise \\str:format "m is not 199, got {?m}") 'nil)
                    (def n (bit:xor 132 67))
                    (if (/= n 199) (raise \\str:format "n is not 199, got {?n}") 'nil)
                    (def o (bit:not 132))
                    (if (/= o -133) (raise \\str:format "o is not -133, got {?o}") 'nil)
                    (def p (bit:nand 132 2))
                    (if (/= p 0) (raise \\str:format "p is not 0, got {?p}") 'nil)
                    (def q (bit:popcount 132))
                    (if (/= q 2) (raise \\str:format "q is not 2, got {?q}") 'nil)
                    (def r (<=> 2 6))
                    (if (/= r -1) (raise \\str:format "r is not -1, got {?r}") 'nil)
                    (def s (<=> 6 2))
                    (if (/= s 1) (raise \\str:format "s is not 1, got {?s}") 'nil)
                    (def t (sqrt 16))
                    (if (/= t 4) (raise \\str:format "t is not 4, got {?t}") 'nil)
                    (def u (signum 16))
                    (if (/= u 1) (raise \\str:format "u is not 1, got {?u}") 'nil)
                    (def v (signum -16))
                    (if (/= v -1) (raise \\str:format "v is not -1, got {?v}") 'nil)
                    (def w (signum 0))
                    (if (/= w 0) (raise \\str:format "w is not 0, got {?w}") 'nil)
                    (def x (round 1.5))
                    (if (/= x 2) (raise \\str:format "x is not 2, got {?x}") 'nil)  
                    (def y (round -1.5))
                    (if (/= y -2) (raise \\str:format "y is not -2, got {?y}") 'nil)
                    (def z (round 1.4))
                    (if (/= z 1) (raise \\str:format "z is not 1, got {?z}") 'nil)
                    (def aa (round -1.4))
                    (if (/= aa -1) (raise \\str:format "aa is not -1, got {?aa}") 'nil)
                    (def bb (round 1.6))
                    (if (/= bb 2) (raise \\str:format "bb is not 2, got {?bb}") 'nil)
                    (def cc (re 3J2))
                    (if (/= cc 3) (raise \\str:format "cc is not 3, got {?cc}") 'nil)
                    (def dd (im 3J2))
                    (if (/= dd 2) (raise \\str:format "dd is not 2, got {?dd}") 'nil)
                    (def ff (re -3J0))
                    (if (/= ff -3) (raise \\str:format "ff is not -3, got {?ff}") 'nil)
                    (def gg (im -3J0))
                    (if (/= gg 0) (raise \\str:format "gg is not 0, got {?gg}") 'nil)
                    (def hh (or 1 0))
                    (if (/= hh 1) (raise \\str:format "hh is not 1, got {?hh}") 'nil)
                    (def ii (or 0 0))
                    (if (/= ii 0) (raise \\str:format "ii is not 0, got {?ii}") 'nil)
                    (def jj (and 1 0))
                    (if (/= jj 0) (raise \\str:format "jj is not 0, got {?jj}") 'nil)
                    (def kk (and 1 1))
                    (if (/= kk 1) (raise \\str:format "kk is not 1, got {?kk}") 'nil)
                    (def ll (not 1))
                    (if (/= ll 0) (raise \\str:format "ll is not 0, got {?ll}") 'nil)
                    (def mm (not 0))
                    (if (/= mm 1) (raise \\str:format "mm is not 1, got {?mm}") 'nil)
                    (def nn (mod 5 2))
                    (if (/= nn 1) (raise \\str:format "nn is not 1, got {?nn}") 'nil)
                    (def oo (mod 5 3))
                    (if (/= oo 2) (raise \\str:format "oo is not 2, got {?oo}") 'nil)
                    (def pp (mod 3.6 2.3))
                    (if (/= pp 1.3) (raise \\str:format "pp is not 1.3, got {?pp}") 'nil)
                    (def qq (mod 3J6.5 2J1.3))
                    (if (/= qq -0.4J-1.4) (raise \\str:format "qq is not -0.4J-1.4, got {?qq}") 'nil)
                    (def rr (min 1 2))
                    (if (/= rr 1) (raise \\str:format "rr is not 1, got {?rr}") 'nil)
                    (def ss (min 2 1))
                    (if (/= ss 1) (raise \\str:format "ss is not 1, got {?ss}") 'nil)
                    (def tt (max 1 2))
                    (if (/= tt 2) (raise \\str:format "tt is not 2, got {?tt}") 'nil)
                    (def uu (max 2 1))
                    (if (/= uu 2) (raise \\str:format "uu is not 2, got {?uu}") 'nil)
                    (def vv (abs 1))
                    (if (/= vv 1) (raise \\str:format "vv is not 1, got {?vv}") 'nil)
                    (def ww (abs -1))
                    (if (/= ww 1) (raise \\str:format "ww is not 1, got {?ww}") 'nil)
                    (def xx (abs 0))
                    (if (/= xx 0) (raise \\str:format "xx is not 0, got {?xx}") 'nil)
                    (def yy (log10 100))
                    (if (/= yy 2) (raise \\str:format "yy is not 2, got {?yy}") 'nil)
                    (def zz (log10 0.01))
                    (if (/= zz -2) (raise \\str:format "zz is not -2, got {?zz}") 'nil)
                    (def aaa (log10 1J3))
                    (if (/= aaa 0.49999998J0.54245370) (raise \\str:format "aaa is not 0.49999998J0.54245370, got {?aaa}") 'nil)
                    (def bbb (log10 1J-3))
                    (if (/= bbb 0.49999998J-0.54245370) (raise \\str:format "bbb is not 0.49999998J-0.54245370, got {?bbb}") 'nil)
                    (def ccc (log2 5))
                    (if (/= ccc 2.3219281) (raise \\str:format "ccc is not 2.3219281, got {?ccc}") 'nil)
                    (def ddd (log2 0.2))
                    (if (/= ddd -2.3219281) (raise \\str:format "ddd is not -2.3219281, got {?ddd}") 'nil)
                    (def eee (log2 1J3))
                    (if (/= eee 1.6609640J1.8019923) (raise \\str:format "eee is not 1.6609640J1.8019923, got {?eee}") 'nil)
                    (def fff (log2 -3))
                    (if (/= fff 1.5849626J4.5323604) (raise \\str:format "fff is not 1.5849626J4.5323604, got {?fff}") 'nil)
                    (def ggg (ln 2))
                    (if (/= ggg 0.69314718) (raise \\str:format "ggg is not 0.69314718, got {?ggg}") 'nil)
                    (def hhh (ln 0.5))
                    (if (/= hhh -0.69314718) (raise \\str:format "hhh is not -0.69314718, got {?hhh}") 'nil)
                    (def iii (ln 1J3))
                    (if (/= iii 1.1512925J1.2490458) (raise \\str:format "iii is not 1.1512925J1.2490458, got {?iii}") 'nil)
                    (def jjj (ln -3))
                    (if (/= jjj 1.0986123J3.1415927) (raise \\str:format "jjj is not 1.0986123J3.1415927, got {?jjj}") 'nil)
                    (def kkk (gcd 5 20))
                    (if (/= kkk 5) (raise \\str:format "kkk is not 5, got {?kkk}") 'nil)
                    (def lll (gcd 20 5))
                    (if (/= lll 5) (raise \\str:format "lll is not 5, got {?lll}") 'nil)
                    (def mmm (gcd 5 0))
                    (if (/= mmm 5) (raise \\str:format "mmm is not 5, got {?mmm}") 'nil)
                    (def nnn (gcd 0 5))
                    (if (/= nnn 5) (raise \\str:format "nnn is not 5, got {?nnn}") 'nil)
                    (def ooo (gcd 0 0))
                    (if (/= ooo 0) (raise \\str:format "ooo is not 0, got {?ooo}") 'nil)
                    (def ppp (gcd 5 20J3))
                    (if (/= ppp 1) (raise \\str:format "ppp is not 1, got {?ppp}") 'nil)
                    (def qqq (gcd 20J3 5))
                    (if (/= qqq 1) (raise \\str:format "qqq is not 1, got {?qqq}") 'nil)
                    (def rrr (gcd 5J3 2.67))
                    (if (/= rrr 0.00J-0.01) (raise \\str:format "rrr is not 1, got {?rrr}") 'nil)
                    (def sss (gcd 2J3 5J3))
                    (if (/= sss -1) (raise \\str:format "sss is not -1, got {?sss}") 'nil)
                    (def ttt (gcd 15.2 35.2))
                    (if (/= ttt 0.8) (raise \\str:format "ttt is not 5.2, got {?ttt}") 'nil)
                    (def uuu (lcm 35.2 15.2))
                    (if (/= uuu 668.8) (raise \\str:format "uuu is not 668.8, got {?uuu}") 'nil)
                    (def vvv (lcm 5 20))
                    (if (/= vvv 20) (raise \\str:format "vvv is not 20, got {?vvv}") 'nil)
                    (def www (lcm 5 0))
                    (if (/= www 0) (raise \\str:format "www is not 0, got {?www}") 'nil)
                    (def xxx (lcm 0 5))
                    (if (/= xxx 0) (raise \\str:format "xxx is not 0, got {?xxx}") 'nil)
                    (def yyy (lcm 0 0))
                    (if (/= yyy 0) (raise \\str:format "yyy is not 0, got {?yyy}") 'nil)
                    (def zzz (lcm 5 20J3))
                    (if (/= zzz 100J15) (raise \\str:format "zzz is not 100J15, got {?zzz}") 'nil)
                    (def aaaa (gamma 0.5))
                    (if (/= aaaa 1.7724539) (raise \\str:format "aaaa is not 1.7724539, got {?aaaa}") 'nil)
                    (def bbbb (gamma 3))
                    (if (/= bbbb 2) (raise \\str:format "bbbb is not 2, got {?bbbb}") 'nil)
                    (def cccc (gamma 1J3))
                    (if (/= cccc 0.019292759J0.033896011) (raise \\str:format "cccc is not 0.019292759J0.033896011, got {?cccc}") 'nil)
                    )
        """));
    }
}