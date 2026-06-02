package com.alexandre.bffagendador_tarefas.infrastructure.exception;

public class ConflictException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConflictException(String message){

        super(message);

    }

    public ConflictException(String message,String string){

        super(message);
    }

	public ConflictException(String string, Throwable cause) {
		
		super(string,cause);
	}

}
