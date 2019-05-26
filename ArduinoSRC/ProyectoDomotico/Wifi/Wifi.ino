#include <SoftwareSerial.h>
SoftwareSerial ESP8266(2, 3); // RX | TX
#define DEBUG true
int x1=0;
void setup()
 { Serial.begin(9600);
 ESP8266.begin(9600);
 pinMode(13,OUTPUT);
 pinMode(12,OUTPUT);
 pinMode(11,OUTPUT);
 pinMode(10,OUTPUT); //EnvioDeDatos("AT+RST\r\n",2000,DEBUG); // resetea el  modulo
  EnvioDeDatos("AT+CWMODE=1\r\n",1000,DEBUG);                           // Cconfigura el modulo en Estación
  //EnvioDeDatos("AT+CWJAP='Name','Password'\r\n",1000,DEBUG);        // datos de tu red.
  EnvioDeDatos("AT+CIFSR\r\n",1000,DEBUG);                              // Nos muestra nuestra Ip asignada.
  EnvioDeDatos("AT+CIPMUX=1\r\n",1000,DEBUG);                           // Configura multiples coneciones
  EnvioDeDatos("AT+CIPSERVER=1,80\r\n",1000,DEBUG);                     // Inicializa el servidor web
 }
void loop()
 { 
 while (ESP8266.available() >0 )                                        // se recibieron datos.
   {char c = ESP8266.read();                                              // lee los datos.
   Serial.print(c);                                                       // Los reenvia por el puerto hardware para que veamos que estamos recibiendo.
   if (c == 71)                                                           // Si recibe un GET al ver la primera letra incia la transmision de datos.
    {Serial.println("peticion recibida");                                 // No dice que ha recibido una peticion.
    delay(1000);
    codigoServidor();
 }
 }
 while(x1==1){
    if(ESP8266.find("+IPD,")){
      delay(500);
      int connectionId = ESP8266.read()-48; 
      ESP8266.find("pin=");
      int pinNumber = (ESP8266.read()-48)*10;
       pinNumber += (ESP8266.read()-48);
      digitalWrite(pinNumber, !digitalRead(pinNumber));
      Serial.print("inviertiendo pin ");
      Serial.println(pinNumber);
    }
  }
 }
void codigoServidor()
{ escribir("<!DOCTYPE HTML>");
 escribir("<html>"); 
 escribir("<head><title>botones arduino esp8266 html</title>"); //nombre de la pestaña que llevara la pagina
 escribir("<body><h1> <FONT SIZE=\"5\" COLOR=\"red\"> ejemplos </h1>"); //titulo del inicio de la pagina
 escribir(" <BODY BGCOLOR=\"black\"> "); 
 escribir("<input type=\"button\" onclick=\"location.href='http://192.168.43.215/pin=13' \" value=\"invertir pin 13\" name=\"boton1\" style='width:100px; height:50px'/>");
 escribir("<input type=\"button\" onclick=\"location.href='http://192.168.43.215/pin=12' \" value=\"invertir pin 12\" name=\"boton2\" style='width:100px; height:50px'/>");
 escribir("<input type=\"button\" onclick=\"location.href='http://192.168.43.215/pin=11' \" value=\"invertir pin 11\" name=\"boton3\" style='width:100px; height:50px'/>");
 escribir("<input type=\"button\" onclick=\"location.href='http://192.168.43.215/pin=10' \" value=\"invertir pin 10\" name=\"boton4\" style='width:100px; height:50px'/>");
 escribir("<DIV align=\"center\"><A HREF=\"http://www.cambatronics.com\" target=\"_blank\">cambatronics.com</a><BR></DIV>");
escribir("</body></html>"); //una pagina web necesita esto <HTML> y </HTML> es el inicio y fin del documento
 
 
 
 x1=1;
 }
void escribir(String text)
{ESP8266.print("AT+CIPSEND=0,"); 
 ESP8266.println(text.length());
 if (ESP8266.find(">")) // Si se recibe el mensaje
 {Serial.println(text);
 ESP8266.println(text); //mandamos el mensaje por el wifi 
 delay(10);
 while ( ESP8266.available() > 0 ) 
 {if ( ESP8266.find("SEND OK") ) //buscamos "ok" y luego salimos
 break; 
 }
 }
}
String EnvioDeDatos(String command, const int timeout, boolean debug)
{
    String cadena = "";
    ESP8266.print(command); // enviar el carácter leído al modulo
    long int time = millis();
    while( (time+timeout) > millis())
    {
      while(ESP8266.available())
      {
        // Muestra los Datos de salida del modulo por el seguimiento de la serie
        char c = ESP8266.read(); //Lee el proximo caracter
        cadena+=c;
      }  
    }
    
    if(debug)
    {
      Serial.print(cadena);
    }
    
    return cadena;
}
