(define mydel
  (lambda (l i)
    
     (cond ((null? l) l)
          ((equal? (car l) i) (cdr l))
          (else (cons (car l) (mydel (cdr l) i)))
          )
    )
  )


(define mymin
  (lambda (l i)
    (cond (( null? l) i)
         ; ((null? (cdr l)) (car l))
          (else (if (< i (car l)) (mymin( cdr l) i) (mymin (cdr l) (car l)))))))
(define sort
  (lambda (l)
    (
     (cond ((null? l) l)
           (else (cons (mymin l (car l)) (sort (mydel l (mymin l (car l))))))))))