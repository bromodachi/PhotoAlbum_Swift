(define L
  '( 0 0 0 0 0 0 0 0 0 0 0 0 0))
(define alpha
  '( (a 0) (b 0) (c 0) (d 0) (e 0) (f 0) (g 0) (h 0) (i 0) (j 0) (k 0) (l 0) (m 0) (n 0)
           (o 0) (p 0) (q 0) (r 0) (s 0) (t 0) (u 0) (v 0) (w 0) (x 0) (y 0) (z 0)))
(define most-frequent-letters
  '(e t a o i n s h r d l c u m w f g y p b v k j x q z))

;;just iterates the alpha list to find the max
(define find-max-frequent
  (lambda (l )
    (cond 
      ((null? l) l)
      (else (find-max-in-list(cdr l) (car(cdr(car l)))))
         )))
;;find-max-frequent helper to find the max.
(define find-max-in-list
  (lambda (l max-optimal)
    (cond
      ((null? l) max-optimal)
      ((equal? (cdr l) '()) max-optimal)
      (else (find-max-in-list (cdr l) (max (car (cdr(car(cdr l)))) max-optimal)))
      )
    )
  )
;;returns the max letter.
(define find-max-letter
  (lambda (l x)
    (cond 
      ((null? l) l)
      ((equal? (car(cdr(car l))) x) (car(car l)))
      (else (find-max-letter (cdr l) x))
      )
    ))

;;takes the alpha list and if it equals the char that was passed by count-frequency, we increment the respective alpha list
(define iterate-list
  (lambda (l x)
         (cond ((null? l) l)
           ((equal? (car(car l))x)
                (cons  (cons (car( car l)) (cons (+ (car (cdr( car l))) 1) '() )) (cdr l)))
               (else (cons (car l) ( iterate-list (cdr l) x)))
               )))

;;counts for a single word, it passes the char to iterate-list where that does the counting for the alpha list
(define count-frequency
  (lambda (l x)
    
    (cond ((null? l) x)
          (else 
          (count-frequency (cdr l) (iterate-list x (car l)))
          )
    )))
;;given a paragraph, it will count the frequency for alpha(should be the n)
(define paragraph-helper-for-counting
  (lambda (p n)
    (cond((null? p) n)
         ;;if it's a list, pass the car first word to the encoder, etc
      ;   ((list? (car p)) (cons ((encode-n n)(car p)) (paragraph-helper(cdr p) n)))
         ((list? (car p)) (paragraph-helper-for-counting  (cdr p) (count-frequency(car p) n)))
   )
 )
  )
  

;(cons  (cons (car( car alpha)) (cons (+ (car (cdr( car alpha))) 1) '() )) (cdr alpha))
(define(vector-test-iterative X Vector)    
      (do ((i 0 (+ i 1))) (< i (vector-length Vector))
          (if (eqv? X (vector-ref Vector i))
              (= i (vector-length Vector))
              (cons (vector-ref Vector i) (ls '())))
          ls))