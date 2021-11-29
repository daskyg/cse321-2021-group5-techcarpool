


function myfunc(){
    var password = document.getElementById("pass").value;
    var re_password = document.getElementById("re_pass").value;

    /*validate password*/
    if(password == ""){        
        document.getElementById("passwordMessage").innerHTML = "**Please fill Password";
        return false;
    
    }else if(re_password == ""){        
        document.getElementById("re_passwordMessage").innerHTML = "**Please fill Password";
        return false;
    }else if (password != re_password) {
        document.getElementById("re_passwordMessage").innerHTML = "**Passwords must match";
        return false;
    }
    
    
    
    
  
}