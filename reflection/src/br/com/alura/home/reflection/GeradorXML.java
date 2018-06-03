package br.com.alura.home.reflection;

import java.lang.reflect.Field;

import br.com.alura.home.reflection.model.Usuario;

public class GeradorXML {
	
	public static String getXML(Object obj) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		
		Class<?> clazz = obj.getClass();
		
		sb.append("<" + clazz.getSimpleName() + "> \n");
		
		// obtendo os atributos declarados na classe (c.geFields() trariam todos os campos inclusive os herdados)
		for(Field field: clazz.getDeclaredFields()) {
			field.setAccessible(true);
			sb.append("<" + field.getName() + ">");
			sb.append(field.get(obj));
			sb.append("</" + field.getName() + "> \n");
		}
		
		sb.append("</" + clazz.getSimpleName() + "> \n");
		return sb.toString();
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin("joao");
		usuario.setSenha("malagueta");
		usuario.setEmail("joao.malagueta@gmail.com");
		usuario.setPapel("palhaço");
		
		String xml = GeradorXML.getXML(usuario);
		
		System.out.println(xml);
	}

}
