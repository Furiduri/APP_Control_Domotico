#include <WiFi.h>
#include <WiFiClient.h>
#include <WiFiServer.h>
#include <WiFiUdp.h>

//Constat
int ledBlue = 3;
int ledGreen = 4;
int ledRed = 5;
int Alert = 6;
int RxD = 0;
int TxD = 1;

void setup() {
  // put your setup code here, to run once:
  pinMode(ledBlue,OUTPUT);
  pinMode(ledGreen,OUTPUT);
  pinMode(ledRed,OUTPUT);
  pinMode(Alert,OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  
}
