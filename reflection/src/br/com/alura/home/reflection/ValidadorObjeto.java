package br.com.alura.home.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.com.alura.home.reflection.model.Usuario;

public class ValidadorObjeto {
	
	public static boolean validar(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean resultado = true;
		
		Class<?> clazz = obj.getClass();
		
		//Acessando métodos da classe (para obter metodos herdados ou com modificador de acesso private usa-se getMethods())
		for(Method method : clazz.getDeclaredMethods()) {
			if(method.getName().startsWith("validar") 
					&& method.getReturnType() == boolean.class 
					&& method.getParameterTypes().length == 0) {
				
				Boolean retorno = (Boolean) method.invoke(obj);
				
				resultado = retorno.booleanValue();
			}
			
		}
		
		return resultado;
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Usuario usuario = new Usuario();
		usuario.setSenha("malagueta");
		usuario.setEmail("joao.malaguetagmail.com");
		
		boolean valido = ValidadorObjeto.validar(usuario);
		
		System.out.println(valido ? "O usuário é valido" : "O usuário é inválido");
		
	}
}
