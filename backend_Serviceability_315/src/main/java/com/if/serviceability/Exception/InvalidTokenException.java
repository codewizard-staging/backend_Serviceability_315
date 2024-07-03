package com.if.serviceability.Exception;

public class InvalidTokenException extends RuntimeException{
  public InvalidTokenException(String message){
    super(message);
  }
}
