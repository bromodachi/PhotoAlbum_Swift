; *********************************************
; *  314 Principles of Programming Languages  *
; *  Spring 2014                              *
; *  Author: Liu Liu                          *
; *          Ulrich Kremer                    *
; *  April 5, 2014                            *
; *********************************************
;; -----------------------------------------------------

(define test-document '(
                        ((h e l l o));;paragraph1
                        ((t h i s)(i s)(t e s t));;paragraph2
                        ))


(define document '(
                   ((t h i s)(c o u r s e)(c o v e r s)(t o p i c s)(i n)(p r o g r a m m i n g)(l a n g u a g e s)(a n d)(c o m p i l e r s))
                   ((a t t r i b u t e)(g r a m m a r s)(a n d)(t h e i r)(u s e)(i n)(s y n t a x)(d i r e c t e d)(t r a n s l a t i o n))
                   ((m o d e l s)(o f)(p r o g r a m m i n g)(l a n g u a g e)(s e m a n t i c s))
                   ((i n t e r m e d i a t e)(r e p r e s e n t a t i o n s)(o f)(p r o g r a m s)(p a r a l l e l)(p r o g r a m m i n g)(m o d e l s))
                   ))
(define another-document '(
                            ((m j q q t))
                            ((y m n x) (n x) (y j x y))
                            ))

(define encoded-document '(((y m n x) (h t z w x j) (h t a j w x) (y t u n h x) (n s) (u w t l w f r r n s l) (q f s l z f l j x) (f s i) (h t r u n q j w x))
 ((f y y w n g z y j) (l w f r r f w x) (f s i) (y m j n w) (z x j) (n s) (x d s y f c) (i n w j h y j i) (y w f s x q f y n t s))
 ((r t i j q x) (t k) (u w t l w f r r n s l) (q f s l z f l j) (x j r f s y n h x))
 ((n s y j w r j i n f y j) (w j u w j x j s y f y n t s x) (t k) (u w t l w f r x) (u f w f q q j q) (u w t l w f r r n s l) (r t i j q x))))