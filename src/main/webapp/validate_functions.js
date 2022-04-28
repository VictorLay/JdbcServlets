//файл с функциями проверки значений 

// -- Проверка значений в форме, вызвавшей функцию "validate" атрибутом "onSubmit"
function validate(form){
    let fail;
    fail = validateName(form.forename.value, "frnm");
    fail += validateName(form.surname.value, "srnm");
    fail += validateUsername(form.username.value);
    fail += validatePassword(form.password.value);

    if   (fail == "")   return true
    else { alert(fail); return false }
}

// -- доп функции для проверки данных формы 
function validateName(field,arg){
  if (field == "") return  "Name: Поле не должно быть пустым. \n";
  else if (field.indexOf(" ") > 0) return "Поле не должно содержать пробелов. \n";
    else if (/[^a-zA-Z-]/.test(field)) {
      if (arg == "srnm") return "В фамилии используйте только символы a-z A-Z и -\n";
      else return "В имени используйте только символы a-z A-Z и -\n";
    } 
    else if (field.length < 4)  
      return "Имя и фамилия должены содержать минимум 4 символа. \n";  //need только символы a-z A-Z и -\n";
  return "";
}

function validateUsername(field){
  if (field == "") return "Username: Поле не должно быть пустым. \n";
  else if (field.length < 6)  return "В имени пользователя должен содержать минимум 6 символа. \n";
        else if (/[^a-zA-Z0-9_-]/.test(field)) return "В имени пользовател разрешены только 'a-z', 'A-Z', '0-9', '-' и '_'.\n";
  return "";
}

function validatePassword(field){
    if (field == "") return "No Password was entered.\n"
    else if (field.length < 6)
        return "Пароль должен содержать минимум 6 символа.\n"
    else if (! /[a-z]/.test(field) ||
             ! /[A-Z]/.test(field) ||
             ! /[0-9]/.test(field))
        return "Passwords require one each of a-z, A-Z and 0-9.\n"
    return ""
}

// функции, выполняющие проверку на вебстранице и сообщающие о неправильно заполненных данных
function valid_nm(name){
    name.onblur = ()=>{   
        f = name.value;
        if ( f == "")
        {
            console.log("Поле name пустое");
            name.classList.remove('invalid');
            name.classList.remove('valid'); 
            }
        else if (/[^a-zA-Z-]/.test(f))
        {
            //alert ("используйте только символы a-z A-Z и -");
            name.classList.add('invalid');
            name.classList.remove('valid'); 
        }
          else 
          {
            if (f.length < 4)  {
              name.classList.add('invalid');
              name.classList.remove('valid'); 
            }
            else {
            name.classList.add('valid');
            name.classList.remove('invalid');
            }
          }
    }
}

function valid_pwd(pswd){
    pswd.onblur = ()=>{
        f = pswd.value;
        if ( f == "")
        {
            console.log("Поле password пустое");
            pswd.classList.remove('invalid');
            pswd.classList.remove('valid'); 
            }
        else if (!(/[a-z]/.test(f)) ||
                 !(/[A-Z]/.test(f)) ||
                 !(/[0-9]/.test(f)) )
        {
            //alert ("используйте как минимум по одному символу a-z, A-Z и 0-9 ");
            pswd.classList.add('invalid');
            pswd.classList.remove('valid'); 
        }
        else 
        {
          if (f.length < 6)  {
            pswd.classList.add('invalid');
            pswd.classList.remove('valid'); 
          }
          else {
          pswd.classList.add('valid');
          pswd.classList.remove('invalid');
          }
        }
    }
}
// функция, отправляющая на сервер запрос ajax и получающая ответ об ункальности данных, введённых в поле
function ajax_valid(user){
    user.onblur = ()=>{
      params  = "uname="+user.value;
      request = new asyncRequest();
  
      request.open("POST", "urlpost.php", true)
      request.setRequestHeader("Content-type",
        "application/x-www-form-urlencoded")
      request.onreadystatechange = function()
      {
        if (this.readyState == 4)
        {
          if (this.status == 200)
          {
            if (this.responseText != null)
            {  
              //alert(this.responseText)
              if (this.responseText == "valid") 
              {
                valid_usrnm(user)         
              }
              else {
              //alert("Пользователь с таким ником уже заегистрован");
              console.log("пользователь с таким именем зарегистрирован");
              user.classList.add('invalid');
              user.classList.remove('valid');
              }
            }
            else alert("Communication error: No data received")
          }
          else alert( "Communication error: " + this.statusText)
        }
      }
      
      request.send(params)
      
      function valid_usrnm(uesrname){
        f = uesrname.value;
        if ( f == "")
        {
          user.classList.remove('valid');
          user.classList.remove('invalid');
          console.log("имя пользователя не введено");
          flag = false;
        }
        else if (/[^a-zA-Z0-9-_]/.test(f)){
            //alert ("используйте только символы a-z, A-Z, 0-9, - и _");
            user.classList.add('invalid');
            user.classList.remove('valid');
            flag = false;
          }
          else if (f.length < 6) {
            console.log("имя пользователя должно содержать минимум 6 символов");
            user.classList.add('invalid');
            user.classList.remove('valid');
            }
            else
            {
              user.classList.add('valid');
              user.classList.remove('invalid');
              flag = true;
            }
        //return flag;
      }
      
      function asyncRequest()
      {
        try
        {
          var request = new XMLHttpRequest()
        }
        catch(e1)
        {
          try
          {
            request = new ActiveXObject("Msxml2.XMLHTTP")
          }
          catch(e2)
          {
            try
            {
              request = new ActiveXObject("Microsoft.XMLHTTP")
            }
            catch(e3)
            {
              request = false
            }
          }
        }
        return request
      }
    }
  }