/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.rd.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class validator {
    private ArrayList<String> errors = new ArrayList();

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
    
    public void validate(Object input, String fieldName, String paramList) {
        
        String[] params;
        if(paramList.contains(":")){
            params = paramList.split(":");
            
        }
        else {
            params = new String[1];
            params[0] = paramList;
            
        }
        
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("date")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);
                try {
                    sdf.parse(input.toString());
                } catch (ParseException e) {
                    System.out.println("Invalid date format for the field <strong>" + fieldName + "</strong>.<br />");
                    errors.add("Invalid date format for the field <strong>" + fieldName + "</strong>.<br />");
                }
            }
            else if (params[i].equals("required")) {
             
                if (input.toString().isEmpty()) {
                    System.out.println("<strong>" + fieldName + "</strong> cannot be left empty.<br />");
                    errors.add(" <div id='error' class=\'alert alert-danger\'><strong>" + fieldName + "</strong> cannot be left empty.</div>");
                }
                
            }
            else if (params[i].contains("max")) {
                Integer len = Integer.valueOf(params[i].substring((params[i].indexOf('_') + 1), params[i].length()));
                if (input.toString().length() > len) {
                    System.out.println("The field <strong>" + fieldName + "</strong> cannot have more than" + len + " characters.<br />");
                }
                errors.add("The field <strong>" + fieldName + "</strong> cannot have more than" + len + " characters.<br />");
            }

            else if (params[i].contains("min")) {
                Integer len = Integer.valueOf(params[i].substring((params[i].indexOf('_') + 1), params[i].length()));
                if (input.toString().length() < len) {
                    System.out.println("The field <strong>" + fieldName + "</strong> should have minimum" + len + " characters.<br />");
                }
                errors.add("The field <strong>" + fieldName + "</strong> should have minimum" + len + " characters.<br />");
            }

            else if (params[i].equals("numeric")) {
                try {
                    Integer.parseInt(input.toString());
                } catch(NumberFormatException e) { 
                    System.out.println(" <strong>" + fieldName + "</strong> should be numeric.<br />");
                    errors.add(" <strong>" + fieldName + "</strong> should be numeric.<br />");
                }   
            }
            else if (params[i].contains("xss")) {

                Pattern pattern = Pattern.compile("^[a-zA-Z0-9_@#$&.+-]*$");
                if (!pattern.matcher(input.toString()).find()) {
                    System.out.println("The field <strong>" + fieldName + "</strong> may contain only alphabets, numbers or any one of ':' , '_' , '@' , '#' , '$' , '&' , '.' , '+' , '-'.<br />");
                }
                errors.add("The field <strong>" + fieldName + "</strong> may contain only alphabets, numbers or any one of ':' , '_' , '@' , '#' , '$' , '&' , '.' , '+' , '-'.<br />");
            }
            else if (params[i].contains("alphanum")) {

                Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
                if (!pattern.matcher(input.toString()).find()) {
                    System.out.println("The field <strong>" + fieldName + "</strong> may contain only alphabets and/or numbers.<br />");
                }
                errors.add("The field <strong>" + fieldName + "</strong> may contain only alphabets and/or numbers.<br />");
            }
            else if (params[i].contains("sql")) {

                String match[] = new String[]{"select", "insert", "delete", "drop", "update", "alter", "modify", "kill", "table", "fetch", "begin", "varchar"};
                int flag = 0;

                for (int k = 0; k < match.length; k++) {
                    if (input.toString().toLowerCase().contains(match[k])) {
                        flag = 1;
                    }

                }
                if (flag == 1) {
                    System.out.println("Invalid value for the field <strong>" + fieldName + "</strong>.<br />");
                    errors.add("Invalid value for the field <strong>" + fieldName + "</strong>.<br />");
                }
            }
            else if (params[i].contains("regtype")) {

                if (!input.equals("Office") && !input.equals("Commissioned")) {
                    System.out.println("Invalid value for the field <strong>" + fieldName + "</strong>.<br />");
                    errors.add("Invalid value for the field <strong>" + fieldName + "</strong>.<br />");
                }
            }
            else if (params[i].contains("email")) {
                if(input == null) {
                    errors.add("Invalid email address specified for the field <strong>"+ fieldName + "</strong>.");
                    break;
                }
                else if(input.toString().length() > 0){
                    try {
                        InternetAddress emailAddr = new InternetAddress ((String) input);
                        emailAddr.validate();
                    } catch (AddressException ex) {
                         System.out.println(ex.getMessage());
                         errors.add("Invalid email address specified for the field <strong>"+ fieldName + "</strong>.");
                         break;
                    } 
                } 
            }  
            else if (params[i].contains("mobile")) {
                if(input == null) {
                    System.out.println("Invalid mobile number specified for the field <strong>"+ fieldName + "</strong>.");
                    errors.add("Invalid mobile number specified for the field <strong>"+ fieldName + "</strong>.");
                    break;
                }
                else if(input.toString().length() > 0){
                    Pattern pattern = Pattern.compile("^\\d{10}$");
                    if (!pattern.matcher(input.toString()).find()) {
                        System.out.println("Invalid mobile number specified for the field <strong>"+ fieldName + "</strong>.");
                        errors.add("Invalid mobile number specified for the field <strong>"+ fieldName + "</strong>.");
                        break;
                    }
                }
            }
            else if (params[i].contains("PIN")) {
                if(input == null) {
                    System.out.println("Invalid PIN specified for the field <strong>"+ fieldName + "</strong>.");
                    errors.add("Invalid PIN specified for the field <strong>"+ fieldName + "</strong>.");
                    break;
                }
                else if(input.toString().length() > 0){
                    Pattern pattern = Pattern.compile("^\\d{6}$");
                    if (!pattern.matcher(input.toString()).find()) {
                        System.out.println("Invalid PIN specified for the field <strong>"+ fieldName + "</strong>.");
                        errors.add("Invalid PIN specified for the field <strong>"+ fieldName + "</strong>.");
                        break;
                    }
                }
            }
            else if (params[i].contains("Pan")) {
                if(input == null) {
                    System.out.println("Invalid PAN specified for the field <strong>"+ fieldName + "</strong>.");
                    errors.add("Invalid PAN specified for the field <strong>"+ fieldName + "</strong>.");
                    break;
                }
                if(input.toString().length() > 0){
                    Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
                    if (!pattern.matcher(input.toString()).find()) {
                        System.out.println("Invalid PAN specified for the field <strong>"+ fieldName + "</strong>.");
                        errors.add("Invalid PAN specified for the field <strong>"+ fieldName + "</strong>.");
                        break;
                    }
                }
            }
            else if (params[i].contains("Aadhar")) {
                if(input.toString().length() > 0){
                    Pattern pattern = Pattern.compile("\\d{12}$");
                    if (!pattern.matcher(input.toString()).find()) {
                        System.out.println("Invalid AADHAR Number specified for the field <strong>"+ fieldName + "</strong>.");
                        errors.add("Invalid AADHAR Number specified for the field <strong>"+ fieldName + "</strong>.");
                        break;
                    }
                    Pattern pattern2 = Pattern.compile("^\\d{12}$");
                    if (!pattern2.matcher(input.toString()).find()) {
                        System.out.println("Invalid AADHAR Number specified for the field <strong>"+ fieldName + "</strong>.");
                        errors.add("Invalid AADHAR Number specified for the field <strong>"+ fieldName + "</strong>.");
                        break;
                    }
                }
            }
        }
    }
    public void validate(Object input, String fieldName, String paramList, String[] list) {        
        String[] params;
        if(paramList.contains(":")){
            params = paramList.split(":");
        }
        else {
            params = new String[1];
            params[0] = paramList;
            
        }
        for (String param : params) {
            if (param.equals("valuein")) {
                if(!Arrays.asList(list).contains((String)input)) {
                    System.out.println("Value specified for field <strong>" + fieldName + "</strong> is not valid.");
                    errors.add("Value specified for field <strong>" + fieldName + "</strong> is not valid.");
                    break;
                }
            }
        }

    }

}
