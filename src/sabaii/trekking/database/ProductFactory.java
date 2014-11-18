package sabaii.trekking.database;

import sabaii.trekking.jpa.JpaDaoFactory;


/**
 * Superclass for MemDaoFactory and JpaDaoFactory
 * @author Sabaii Soft. SKE10
 *
 */
public class ProductFactory {
	
	private static ProductFactory factory;
	protected ProductDao daoInstance;
/**
 * return instance of DaoFactory ( MemDaoFactory or JpaDaoFactory)
 * @return
 */
	public static ProductFactory getInstance() {
		if (factory == null) factory = new JpaDaoFactory();
		return factory;
	}

	public ProductFactory() {
		super();
	}

	public ProductDao getContactDao() {
		return daoInstance;
	}
/**
 * handle when shutdown webservice
 * keep Database for next open 
 */
	public void shutdown() { 
		
	}

}