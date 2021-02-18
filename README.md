# validator
A generic input validation class written in Java.

## How can I use it?
```java
Validator validator = new Validator();
validator.validate(request.getParameter("serialno"), "Serial number", "required:numeric");
validator.validate(request.getParameter("username"), "User name", "required:alphanum");
ArrayList<String> errors = validator.getErrors();
if (!errors.isEmpty()) {
    String msg = new String();
    for (String error : errors)         
      msg = msg + error;
    }
} 
```
## Available validations

#### 1. reqiured
#### 2. max
#### 3. min
#### 4. numeric
#### 5. xss
#### 6. alphanum
#### 7. sql
#### 8. regtype
#### 9. email
#### 10. mobile
#### 11. PIN
#### 12. Pan
#### 13. Aadhar

