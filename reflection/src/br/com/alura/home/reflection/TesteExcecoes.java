package br.com.alura.home.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TesteExcecoes {
	
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		// Instanciando Inner Class
		Class<?> excecoesClass =  Class.forName("br.com.alura.home.reflection.TesteExcecoes$Excecoes");
		Excecoes excecoes = (Excecoes) excecoesClass.newInstance();
		
		// invocando metodo do objeto instanciado
		Method method = excecoesClass.getMethod("method", String.class);
		method.invoke(excecoes, "Vamos ver se funciona");
		
		// invocando método que lança exceção
		try {
			Method methodThrowsException = excecoesClass.getMethod("methodThrowsException", String.class);
			methodThrowsException.invoke(excecoes, "Exceção será lançada");
		}catch(InvocationTargetException e) {
			//Pegando exceção original lançada pelo método invocado
			e.getTargetException().printStackTrace();
		}
		
	}
	
	static class Excecoes{
		
		public void method(String s) {
			System.out.println(s);
		}
		
		public void methodThrowsException(String s) throws IllegalAccessException {
			throw new IllegalAccessException(s);
		}
	}

}
