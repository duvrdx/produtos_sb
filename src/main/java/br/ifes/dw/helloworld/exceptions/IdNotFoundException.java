package br.ifes.dw.helloworld.exceptions;

public class IdNotFoundException extends RuntimeException{
    public  IdNotFoundException(){
        super("Id is not found or don't exists");
    }
}
