package br.com.alura.home.reflection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Mapeador {

	private Map<Class<?>, Class<?>> mapa = new HashMap<>();

	public void load(String nomeArquivo) throws FileNotFoundException, IOException, ClassNotFoundException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(nomeArquivo));

		// Obtendo classes
		for (Object key : prop.keySet()) {
			Class<?> interf = Class.forName(key.toString());
			Class<?> impl = Class.forName(prop.get(key).toString());

			if (!interf.isAssignableFrom(impl)) {
				throw new RuntimeException(
						"A classe " + impl.getName() + " não implementa a interface " + interf.getName());
			}

			mapa.put(interf, impl);
		}
	}

	// obter implementação
	public Class<?> getImplementation(Class<?> interf) {
		return mapa.get(interf);
	}

	// obter instancia simples (construtor padrão)
	@SuppressWarnings("unchecked")
	public <E> E getInstance(Class<E> interf) throws InstantiationException, IllegalAccessException {
		Class<?> impl = getImplementation(interf);
		return (E) impl.newInstance();
	}

	// obter instancia com construtores parametrizados
	@SuppressWarnings("unchecked")
	public <E> E getInstance(Class<E> interf, Object... params) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> impl = getImplementation(interf);
		Class<?>[] tiposConstrutor = new Class<?>[params.length];

		for (int i = 0; tiposConstrutor.length > i; i++) {
			Class<?> param = params[i].getClass();
			
			//Verifica quais são os tipos primitivos
			if(param.isAssignableFrom(Byte.class)) {
				tiposConstrutor[i] = Byte.TYPE;
			}else if(param.isAssignableFrom(Short.class)) {
				tiposConstrutor[i] = Short.TYPE;
			}else if(param.isAssignableFrom(Character.class)) {
					tiposConstrutor[i] = Character.TYPE;
			}else if(param.isAssignableFrom(Integer.class)) {
				tiposConstrutor[i] = Integer.TYPE;
			}else if(param.isAssignableFrom(Float.class)) {
				tiposConstrutor[i] = Float.TYPE;
			}else if(param.isAssignableFrom(Double.class)) {
				tiposConstrutor[i] = Double.TYPE;
			}else {
				tiposConstrutor[i] = param;
			}
		}

		Constructor<?> constructor = impl.getConstructor(tiposConstrutor);

		return (E) constructor.newInstance(params);
	}

	public static void main(String[] args) throws Exception {
		Mapeador mapeador = new Mapeador();
		mapeador.load("resources/classes.properties");

		// Obtendo classes
		System.out.println(mapeador.getImplementation(Set.class));
		System.out.println(mapeador.getImplementation(Map.class));

		System.out.println("\n");
		
		//Obtendo instancias
		Set<?> conjunto = mapeador.getInstance(Set.class);
		System.out.println("Objeto instanciado com construtor padrão " + conjunto.getClass());
		 
		conjunto = mapeador.getInstance(Set.class, 5, 10.0f);
		System.out.println("Objeto instanciado com construtor parametrizado " + conjunto.getClass());
	}
}
