package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.DoctorsDAO;
import com.softserve.edu.lv251.entity.Appointments;
import com.softserve.edu.lv251.entity.Doctors;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by kilopo on 13.07.2017.
 */
@Transactional
@Repository
public class DoctorsDAOImpl extends BaseDAOImpl<Doctors> implements DoctorsDAO {
    @Override
    public List<Doctors> searchByLetters(String letters) {
        String search=letters+"%".toLowerCase();
       Query query= entityManager.createQuery("from Doctors d where lower(d.firstname) like" +
               " :letters or lower(d.lastname) like :letters or lower(d.specialization.name) like :letters").setParameter("letters",search);
        return query.getResultList();
    }

    public List<Appointments> appointmentsInThisMonth(Long id, Date date)
    {
        return  entityManager
                .createQuery("from Appointments a where month(date) = month(a.appointmentDate) and a.doctors.id = id" +
                        " and year(date) = year(a.appointmentDate)")
                .getResultList();
    }


    @Override
    public List<Doctors> searchBySpecialization(String name) {
        Query query=entityManager.createQuery("select d from Doctors d join d.specialization s where s.name like :name ").setParameter("name",name);
        return query.getResultList();
    }


    @Override
     public List<Doctors> searchByDistrict(String name) {
              Query query=entityManager.createQuery("select d from Doctors d join d.clinics c join c.contact cont join cont.district dist where" +
                              " dist.name like :name").setParameter("name",name);
               return query.getResultList();
           }


    @Override
    public List<Doctors> getWithOffsetAndLimit(int offset, int limit) {
        Query query = entityManager.createQuery(
                "select d " +
                        "from Doctors d")
                .setFirstResult(offset)
                .setMaxResults(limit);

        return query.getResultList();

    }

    @Override
    public List<Doctors> searchByNameAndSpecialisationWithOffsetAndLimit(String value, int offset, int limit) {
        String name = "%" + value + "%";

        Query query=entityManager.createQuery(
                "select d " +
                "from Doctors d " +
                        "join d.specialization s " +
                        "where s.name + d.firstname + d.lastname + d.middlename like :name ")
                .setParameter("name",name)
                .setFirstResult(offset)
                .setMaxResults(limit);

        return query.getResultList();
    }

}
