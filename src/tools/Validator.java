/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Amanda
 */
public class Validator {
    
    public static boolean cpf(String CPF){
        return false;
    }
    
    public static boolean validateCPF(String cpf) {
        if(!Validator.validateRequired(cpf))
            return false;
        
        if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
            return false;

          try {
               Long.parseLong(cpf);
          } catch (NumberFormatException e) { // CPF não possui somente números
           return false;
          }

      return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
    }
    
    private static boolean isCPFPadrao(String cpf) {
          if (cpf.equals("11111111111") || cpf.equals("22222222222")
        || cpf.equals("33333333333")
        || cpf.equals("44444444444")
        || cpf.equals("55555555555")
        || cpf.equals("66666666666")
        || cpf.equals("77777777777")
        || cpf.equals("88888888888")
        || cpf.equals("99999999999")) {

               return true;
          }

      return false;
    }
    
    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++)
           soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        if (soma % 11 == 0 | soma % 11 == 1)
           primDig = new Integer(0);
        else
           primDig = new Integer(11 - (soma % 11));

      soma = 0;
          peso = 11;
          for (int i = 0; i < num.length(); i++)
               soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

          soma += primDig.intValue() * 2;
          if (soma % 11 == 0 | soma % 11 == 1)
               segDig = new Integer(0);
          else
               segDig = new Integer(11 - (soma % 11));

          return primDig.toString() + segDig.toString();
    }
    
    public static boolean validateNumber(String n){
        
        try{
            double number = Double.parseDouble(n);    
            
            return true;
        }catch(NumberFormatException e){
            return false;
        }
       
    }
    
    public static boolean validatePassword(String password) {
        if(password.length() < 6)
            return false;
        
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(password);
        if(!m.find()){
            return false;
        }
        
        p = Pattern.compile("[0-9]+");
        m = p.matcher(password);
        
        if(!m.find()){
            return false;
        }
        
        return true;
    }
    
    public static boolean validateTelephone(String telephone){
        if(!Validator.validateNumber(telephone)){
            return false;
        }
        
        if(telephone.length() < 8 || telephone.length() > 9){
            return false;
        }
        
        if(!Validator.validateRequired(telephone)){
            return false;
        }
        
        return true;
    }
    
    public static boolean validateRequired(String s){
        return s.length() > 0;
    }
    
    public static boolean validateEmail(String email){
        if(!Validator.validateRequired(email)){
            return false;
        }
        
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email);
        
        return m.matches();
    }
    
    public static boolean validateDate(String date){
        if(!Validator.validateRequired(date)){
            return false;
        }
        
        Pattern p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(date);
        
        return m.matches();
    }
    
    public static boolean validateLink(String url){
        if(!Validator.validateRequired(url)){
            return false;
        }
        
        Pattern p = Pattern.compile("(http:\\/\\/)*([A-za-z0-9])+(\\.[A-za-z0-9]+)+");
        Matcher m = p.matcher(url);
        
        return m.matches();
    }
    
}
