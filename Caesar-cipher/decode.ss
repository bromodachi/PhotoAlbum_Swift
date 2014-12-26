; *********************************************
; *  314 Principles of Programming Languages  *
; *  Spring 2014                              *
; *  Author: Liu Liu                          *
; *          Ulrich Kremer                    *
; *  April 5, 2014                            *
; *********************************************
;; -----------------------------------------------------
;; ENVIRONMENT
;; contains "ctv", "vtc",and "reduce" definitions
(load "include.ss")

;; contains a test document consisting of three paragraphs. 
(load "document.ss")

;; contains a test-dictionary, which has a much smaller dictionary for testing
;; the dictionary is needed for spell checking
;;(load "test-dictionary.ss")

 (load "dictionary.ss") ;; the real thing with 45,000 words


;; -----------------------------------------------------
;; HELPER FUNCTIONS
; my double function
;;(define double (lambda (l)
 ;;  (cond
 ;; [(null? l) '()]
 ; [(list? (car l)) (cons (double (car l)) (double ( cdr l)))]
 ; [else (cons (car l) (cons(car l) (double(cdr l)))) ])))

;; *** CODE FOR ANY HELPER FUNCTION GOES HERE ***
;; ***following is just to help enocde the paragraph of a document ***
(define L
  '( (a 0) (b 0) (c 0) (d 0) (e )))
(define paragraph-helper
  (lambda (p n)
    (cond((null? p) p)
         ;;if it's a list, pass the car first word to the encoder, etc
      ;   ((list? (car p)) (cons ((encode-n n)(car p)) (paragraph-helper(cdr p) n)))
         ((list? (car p)) (cons (n(car p)) (paragraph-helper(cdr p) n)))
   )
 )
  )

;;-----BRUTE FORCE HELPER METHODS------
;;my spell checker takes a list from the dictionary and checks every word until we find a hit.
(define my-spell-checker
  (lambda (w l)
          (cond ((null? l) #f)
          ((equal? w (car l))#t)
          (else (my-spell-checker w (cdr l)))
                )
  )) 
;;for the brute force method. Creates a list of true or falses if we get a hit with spell checker
(define My-Worker-A
  (lambda (p)
    (cond((null? p) p)
         ((list? (car p)) (cons (my-checker (car p) 0) (My-Worker-A (cdr p))))
   )
    ))


;;test1 justs goes through the list one by one until we find a hit
(define test1
  (lambda (w)
    (cond ((null? w) w)
          (else 
           (cons(vtc(modulo(- (ctv(car w))1)26)) (test1 (cdr w)))
           ))
    ))


;;does nothing
(define test3
  (lambda (n)
    (cond ((eq? n 26) n)
         (else (test3 (+ n 1))))))

;;my-checker returns a true or false value and checks with the dictionary
(define my-checker
  (lambda (w n)
      ;(test1 w)
      (cond
      ((eq? n 26) #f)
      ((equal? (spell-checker w) #t) #t)
      (else (my-checker (test1 w) (+ n 1)))
      )
      
      ))

;;my-worker-AB returns the n for the brute force decoder
(define my-worker-AB
  (lambda (w n)
    (cond
      ((eq? n 26) #f)
      ((equal? (spell-checker w) #t) n)
      (else (my-worker-AB (test1 w) (+ n 1)))
      )
      
      ))
;;exactly like the encode but instead, modified to take a minus
(define decode-n
  (lambda (n);;"n" is the distance, eg. n=3: a->d,b->e,...z->c
    (lambda (w);;"w" is the word to be encoded
      (cond (( null? w) w)
            (else 
                   
      ;;  (cons ((vtc(modulo(+ (ctv(car w))n)26)) ((encode-n n) (cdr w))))
        (cons(vtc(modulo(- (ctv(car w))n)26)) ((decode-n n) (cdr w)))
      )))))

;;------------------------------------DECODE HELPER METHODS FOR METHOD B--------
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

;;finds the distance between most frequent list
(define find-distance
  (lambda (x y)
    ; let x be the decoded distance, let y be from the frequent list
    (- (ctv  x) (ctv  y))
    ))
;;apply distance to the words.
(define apply-distance
  (lambda (w x y)
    (cond 
      ((null? y) #f)
  ;  ((equal? (spell-checker((encode-n (find-distance x (car y)))w)) #t) ((encode-n (find-distance x (car y)))w))
      ((equal? (spell-checker((encode-n (find-distance x (car y)))w)) #t) (find-distance x (car y)))
    (else (apply-distance w x (cdr y)))
    )
    ))
;;will create a lists of true and false for the my-gen-coder-b
;; x should be the original list of the paragraph
(define My-Worker-For-Frequency-B
  (lambda (p x)
    (cond((null? p) p)
         ((list? (car p)) (cons (apply-distance-true-false (car p)
(find-max-letter (paragraph-helper-for-counting x alpha)(find-max-frequent(paragraph-helper-for-counting x alpha)))
most-frequent-letters) (My-Worker-For-Frequency-B (cdr p) x)))
   )
    ))

;;apply distance but this time returns a true or false value
(define apply-distance-true-false
  (lambda (w x y)
    (cond 
      ((null? y) #f)
    ((equal? (spell-checker((encode-n (find-distance x (car y)))w)) #t) #t)
    (else (apply-distance-true-false w x (cdr y)))
    )
    ))
    
;(define letsGo
;  (lambda (w n)
 ;        (cond ((equal? n 26) #f)
 ;              (else (test1 w)
 ;               )
 ;              )

;; -----------------------------------------------------
;; SPELL CHECKER FUNCTION

;;check a word's spell correctness
;;INPUT:a word(a global variable "dictionary" is included in the file "test-dictionary.ss", and can be used directly here)
;;OUTPUT:true(#t) or false(#f)
(define spell-checker 
  (lambda (w)
    (my-spell-checker w dictionary)
        ;  (else (spell
  ;; 'SOME_CODE_GOES_HERE ;; *** FUNCTION BODY IS MISSING *** 
   ))

;; -----------------------------------------------------
;; ENCODING FUNCTIONS

;;generate an Caesar Cipher single word encoders
;;INPUT:a number "n"
;;OUTPUT:a function, whose input=a word, output=encoded word
(define encode-n
  (lambda (n);;"n" is the distance, eg. n=3: a->d,b->e,...z->c
    (lambda (w);;"w" is the word to be encoded
      (cond (( null? w) w)
            (else 
                   
      ;;  (cons ((vtc(modulo(+ (ctv(car w))n)26)) ((encode-n n) (cdr w))))
        (cons(vtc(modulo(+ (ctv(car w))n)26)) ((encode-n n) (cdr w)))
      )))))



;;encode a document
;;INPUT: a document "d" and a "encoder"
;;OUTPUT: an encoded document using a provided encoder
(define encode-d;;this encoder is supposed to be the output of "encode-n"
  (lambda (d encoder)
    (cond ((null? d) d)
          ;(cons ((encode-n 5)(car p)) (paragraph-helper(cdr p))))
          ((list? d) (cons (paragraph-helper(car d) encoder) (encode-d(cdr d) encoder))))
 ;   'SOME_CODE_GOES_HERE ;; *** FUNCTION BODY IS MISSING ***
    ))
    
;; -----------------------------------------------------
;; DECODE FUNCTION GENERATORS
;; 2 generators should be implemented, and each of them returns a decoder

;;generate a decoder using brute-force-version spell-checker
;;INPUT:an encoded paragraph "p"
;;OUTPUT:a decoder, whose input=a word, output=decoded word
(define Gen-Decoder-A
  (lambda (p)
    (lambda (w)
    (cond((null? p) p)
         ;;if it's a list, pass the car first word to the encoder, etc
      ;   ((list? (car p)) (cons ((encode-n n)(car p)) (paragraph-helper(cdr p) n)))
         ((equal? (reduce equal? (My-Worker-A  p) #t) #t) ((decode-n (my-worker-AB (car p) 0)) w))
         ; ((equal? (reduce equal? (My-Worker-A  p) #t) #t) (my-worker-AB (car p) 0))
   )
  ;  (lambda (x) decode-n x)
   ; 'SOME_CODE_GOES_HERE ;; *** FUNCTION BODY IS MISSING ***
    )))

;;generate a decoder using frequency analysis
;;INPUT:same as above
;;OUTPUT:same as above
(define Gen-Decoder-B
  (lambda (p)
    (lambda (w)
    (cond((null? p) p)
         ;;if it's a list, pass the car first word to the encoder, etc
      ;   ((list? (car p)) (cons ((encode-n n)(car p)) (paragraph-helper(cdr p) n)))
         ((equal? (reduce equal? (My-Worker-For-Frequency-B p p) #t) #t) 
          ((encode-n (apply-distance (car p)
                                     (find-max-letter (paragraph-helper-for-counting p alpha)(find-max-frequent(paragraph-helper-for-counting p alpha)))
                                     most-frequent-letters)) w))
         ; ((equal? (reduce equal? (My-Worker-A  p) #t) #t) (my-worker-AB (car p) 0))
   )
  ;  (lambda (x) decode-n x)
   ; 'SOME_CODE_GOES_HERE ;; *** FUNCTION BODY IS MISSING ***
    )))

;; -----------------------------------------------------
;; CODE-BREAKER FUNCTION

;;a codebreaker
;;INPUT: an encoded document(of course by a Caesar's Cipher), a decoder(generated by functions above)
;;OUTPUT: a decoded document
(define Code-Breaker
  (lambda (d decoder)
    
     (cond ((null? d) d)
          ;(cons ((encode-n 5)(car p)) (paragraph-helper(cdr p))))
          ((list? d) (cons (paragraph-helper(car d) decoder) (Code-Breaker(cdr d) decoder))))
  ;   'SOME_CODE_GOES_HERE ;; *** FUNCTION BODY IS MISSING ***
     )) 

;; -----------------------------------------------------
;; EXAMPLE APPLICATIONS OF FUNCTIONS
;;(spell-checker '(h e l l o))
;;(define add5 (encode-n 5))
;;(encode-d document add5)
;;(define decoderSP1 (Gen-Decoder-A paragraph))
;;(define decoderFA1 (Gen-Decoder-B paragraph))
;;(Code-Breaker document decoderSP1)
