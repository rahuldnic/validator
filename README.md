# validator
A generic input validation class in Java.

# Example

```java
Validator validator = new Validator();
validator.validate(request.getParameter("serialno"), "Serial number", "required");
validator.validate(request.getParameter("username"), "User name", "required");
ArrayList<String> errors = validator.getErrors();
if (!errors.isEmpty()) {
    String msg = new String();
    for (String error : errors)         
      msg = msg + error;
    }
} 
```
