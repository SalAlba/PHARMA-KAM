package pl.kielce.tu.pharmacy.core.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kielce.tu.pharmacy.core.model.DefaultPrescription;
import pl.kielce.tu.pharmacy.core.model.Prescription;

/**
 * Session Bean implementation class DefaultPrescriptionDAO
 */
@Stateless
public class DefaultPrescriptionDAO implements PrescriptionDAO 
{
	@PersistenceContext(name = "core-impl")
	private EntityManager entityManager;

	@Override
	public Prescription createPrescription(Prescription prescription) {
		entityManager.persist(prescription);
		return prescription;
	}
	
	@Override
	public Prescription findPrescription(int id) {
		return entityManager.find(DefaultPrescription.class, id);
	}
	
	@Override
	public void removePrescription(Prescription prescription) {
		entityManager.remove(prescription);
	}

	@Override
	public void updatePrescription(Prescription prescription) {
		entityManager.merge(prescription);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Prescription> selectPrescriptions() 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultPrescription.findAll");
			return (List<Prescription>) query.getResultList();
		
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Prescription> selectPrescriptionByPrescriptionCode(int prescriptionCode) 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultPrescription.findByPrescriptionCode");
			query.setParameter("prescriptionCode", prescriptionCode);
			return (List<Prescription>) query.getResultList();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> selectProductLabelByPrescriptionCode(int prescriptionCode) 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultPrescription.findProductByPrescriptionCode");
			query.setParameter("prescriptionCode", prescriptionCode);
			return (List<Integer>) query.getResultList();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
}
