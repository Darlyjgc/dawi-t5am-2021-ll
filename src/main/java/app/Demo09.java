package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Usuario;

public class Demo09 {
	
	public static void main(String[] args) {
		//Obtener la conexión -> según unidad de persistencia -> DAOFactory fabrica
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//Crear los DAOS, pero usando la fábrica
		EntityManager em = fabrica.createEntityManager();
		
		//Validar un usuario según su usuario y clave -> Usando procedures
		String sql = "{call usp_validaAcceso (:xusr, :xcla)}";		
		
		Query query = em.createNativeQuery(sql, Usuario.class);
		query.setParameter("xusr", "U002@gmail.com");
		query.setParameter("xcla", "10002");
		
		Usuario u = null;
		try {
			u = (Usuario) query.getSingleResult();
		} catch (Exception e) {
			
		}
		
		if(u ==null) {
			System.out.println("Código no existe");
		}else {
			System.out.println("Bienvenido " + u.getNombre() + " " + u.getApellido());
			System.out.println(u);
		}				
		em.close();
	}

}
