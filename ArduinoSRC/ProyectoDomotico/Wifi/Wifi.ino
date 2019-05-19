#include <SoftwareSerial.h>
SoftwareSerial BT1(0, 1); // RX | TX

void setup(){
     Serial.begin(115200);
     delay(500);
     Serial.println("Arrancando...");
     BT1.begin(115200);
     delay(500);
  }

void loop(){
     if (BT1.available())
         { char c = BT1.read() ;
           Serial.print(c);
         }
     if (Serial.available())
         {  char c = Serial.read();
            BT1.print(c);
         }
   }
