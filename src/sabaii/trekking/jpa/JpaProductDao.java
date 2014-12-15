package sabaii.trekking.jpa;

import java.net.URLEncoder;
import java.util.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import sabaii.trekking.database.ProductDao;
import sabaii.trekking.entity.Link;
import sabaii.trekking.entity.Product;

import java.util.logging.Logger;

import jersey.repackaged.com.google.common.collect.Lists;

/**
 * Data access object for saving and retrieving contacts,
 * using JPA.
 * To get an instance of this class use:
 * <p>
 * <tt>
 * dao = DaoFactory.getInstance().getContactDao()
 * </tt>
 * 
 * @author jim
 */
public class JpaProductDao implements ProductDao {
	/** the EntityManager for accessing JPA persistence services. */
	private final EntityManager em;
	
	/**
	 * constructor with injected EntityManager to use.
	 * @param em an EntityManager for accessing JPA services.
	 */
	public JpaProductDao(EntityManager em) {
		this.em = em;
//		createTestContact( );
	}


	/**
	 * @see sabaii.trekking.database.ProductDao#find(long)
	 */
	@Override
	public Product find(long id) {
		Product product = em.find(Product.class, id);  // isn't this sooooo much easier than JDBC?
		product.setLink(new Link(createLink(product)));
		return product;
	}

	/**
	 * @see sabaii.trekking.database.ProductDao#findAll()
	 */
	@Override
	public List<Product> findAll() {
		Query query = em.createQuery("SELECT p FROM Product p");
		List<Product> products = query.getResultList();
		for (Product product : products) {
			product.setLink(new Link(createLink(product)));
		}
		return products;
	}

	private String createLink(Product product) {
		return "http://แม้พแฟ้พ.ไทย/product/" + product.getName().replace(" ", "%20") + "?ref=" + product.getId();
	}


	/**
	 * Find contacts whose title contains string
	 * @see sabaii.trekking.database.ProductDao#findByTitle(java.lang.String)
	 */
	@Override
	public List<Product> findByName(String titlestr) {
		// LIKE does string match using patterns.
		Query query = em.createQuery("select p from Product p where LOWER(p.name) LIKE :name");
		// % is wildcard that matches anything
		query.setParameter("name", "%"+titlestr.toLowerCase()+"%");
		// now why bother to copy one list to another list?
		List<Product> products = Lists.newArrayList( query.getResultList() );
		for (Product product : products) {
			product.setLink(new Link(createLink(product)));
		}
		return products;
	}

	/**
	 * @see sabaii.trekking.database.ProductDao#delete(long)
	 */
	@Override
	public boolean delete(long id) {
		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		Product contact = em.find(Product.class, id);
		if (contact == null) 
		{
			tx.commit();
			return false;
		}
		em.remove(contact);
		tx.commit();
		//TODO implement this.  See save for example
		return true;
		
	}
	
	/**
	 * @see sabaii.trekking.database.ProductDao#save(sabaii.trekking.entity.Product)
	 */
	@Override
	public boolean save(Product contact) {
		if (contact == null) throw new IllegalArgumentException("Can't save a null contact");
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(contact);
			tx.commit();
			return true;
		} catch (EntityExistsException ex) {
			Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
			if (tx.isActive()) try { tx.rollback(); } catch(Exception e) {}
			return false;
		}
	}

	/**
	 * @see sabaii.trekking.database.ProductDao#update(sabaii.trekking.entity.Product)
	 */
	@Override
	public boolean update(Product update) {
		em.getTransaction().begin();
		Product contact = em.find(Product.class,update.getId());
		em.merge(update);
		em.getTransaction().commit();
		
		return false;
	}
}
