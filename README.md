# validator
A generic input validation class in Java.
# Example

Validator validator = new Validator();
validator.validate(request.getParameter("DocRefNo"), "Document Serial Number", "required");
validator.validate(request.getParameter("PartyType"), "Party Type", "required");

ArrayList<String> errors = validator.getErrors();

if (!errors.isEmpty()) {
    String msg = new String();
    for (String error : errors)         
      msg = msg + error;
    }
} 

