package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Usuario;

public class Demo08 {
	
	public static void main(String[] args) {
		//Obtener la conexi�n -> seg�n unidad de persistencia -> DAOFactory fabrica
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//Crear los DAOS, pero usando la f�brica
		EntityManager em = fabrica.createEntityManager();
		
		//Validar un usuario seg�n su usuario y clave
		String sql = "select u from Usuario u where u.usuario = :xusr and u.clave = :xcla";		
		
		TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
		query.setParameter("xusr", "U002@gmail.com");
		query.setParameter("xcla", "10002");
		
		Usuario u = null;
		try {
			u = query.getSingleResult();
		} catch (Exception e) {
			
		}
		
		if(u ==null) {
			System.out.println("C�digo no existe");
		}else {
			System.err.println("Bienvenido " + u.getNombre() + " " + u.getApellido());
			System.out.println(u);
		}				
		em.close();
	}

}
