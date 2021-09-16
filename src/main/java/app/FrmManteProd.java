package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Producto;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JTextArea tblProducto;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtCodigo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnRegistrar;
	private JButton btnListado;
	private JButton btnBuscar;
	private JTextField txtEstado;
	private JTextField txtCategoria;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("MANTENIMIENTO DE PRODUCTOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(364, 29, 89, 23);
		contentPane.add(btnRegistrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 208, 443, 203);
		contentPane.add(scrollPane);
		
		tblProducto = new JTextArea();
		scrollPane.setViewportView(tblProducto);
		
		btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(175, 422, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 30, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id. Producto :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa :");
		lblCategora.setBounds(10, 153, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n :");
		lblDescripcin.setBounds(10, 64, 102, 14);
		contentPane.add(lblDescripcin);
		
		JLabel lblNewLabel_1_1 = new JLabel("Stock :");
		lblNewLabel_1_1.setBounds(10, 95, 102, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Precio :");
		lblNewLabel_1_2.setBounds(10, 124, 102, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Estado :");
		lblNewLabel_1_3.setBounds(10, 183, 102, 14);
		contentPane.add(lblNewLabel_1_3);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 61, 86, 20);
		contentPane.add(txtDescripcion);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 92, 86, 20);
		contentPane.add(txtStock);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 121, 86, 20);
		contentPane.add(txtPrecio);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(364, 68, 89, 23);
		contentPane.add(btnActualizar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(364, 102, 89, 23);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(364, 136, 89, 23);
		contentPane.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(218, 29, 89, 23);
		contentPane.add(btnBuscar);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(122, 177, 86, 20);
		contentPane.add(txtEstado);
		
		txtCategoria = new JTextField();
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(122, 150, 86, 20);
		contentPane.add(txtCategoria);
		
		llenaCombo();
	}


	void registrar() {
		String codigo = leerCodigo();
		String descripcion = leerDescripcion();
		int stock= leerStock();
		double precio = leerPrecio();
		int categoria = leerCategoria();
		int estado = leerEstado();
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		Query query = em.createNativeQuery("{call usp_registroProducto(:cod, :descr, :stock, :pre, :cat, :est)}", Producto.class);
		query.setParameter("cod", "idprod");
		query.setParameter("descr", "descripcion");
		query.setParameter("stock", "stock");
		query.setParameter("pre", "precio");
		query.setParameter("cat", "idcategoria");
		query.setParameter("est", "estado");
		
		Producto p = new Producto();
		p.setIdprod(codigo);
		p.setDescripcion(descripcion);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setIdcategoria(categoria);
		p.setEstado(estado);
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		System.out.println("Producto" + " " + p.getDescripcion() + "registrado con éxito!");
		em.close();
	}
	
	void editar() {
		
		
	}
	
	void actualizar() {
		
		
	}
	
	void llenaCombo() {
		
	}
	
	void buscar() {
	}

	private String leerCodigo() {
		return txtCodigo.getText();
	}
	
	private String leerDescripcion() {
		return txtDescripcion.getText();
	}
	
	private int leerStock() {
		return Integer.parseInt(txtStock.getText());
	}
	
	private int leerCategoria() {
		return Integer.parseInt(txtCategoria.getText());
	}
	
	private int leerEstado() {
		return Integer.parseInt(txtEstado.getText());
	}
	
	private double leerPrecio() {
		return Double.parseDouble(txtPrecio.getText());
	}
	
	void listado() {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		Query query = em.createNativeQuery("{call usp_listadoProducto()}", Producto.class);
		
		List<Producto> listadoProductos = query.getResultList();
		
		
		for (Producto p : listadoProductos) {
			tblProducto.append(p.getIdprod() + "\t" + p.getDescripcion() + "\t" + p.getStock() + "\t" + p.getPrecio() + "\n");
		}
		
		em.close();
	}
}
