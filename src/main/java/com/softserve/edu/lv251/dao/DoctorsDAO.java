package com.softserve.edu.lv251.dao;

import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.entity.Doctors;

import java.util.Date;
import java.util.List;

/**
 * Created by Taras on 16.07.2017.
 */
public interface DoctorsDAO extends BaseDAO<Doctors>{
    List<Doctors> searchByLetters(String letters);
    List<Appointment> appointmentsInThisMonth(Long id, Date date);
    List<Doctors>searchByDistrict(String name);
    List<Doctors>searchBySpecialization(String name);
    List<Doctors>getWithOffsetAndLimit(int offset, int limit);
    List<Doctors>searchByNameAndSpecialisationWithOffsetAndLimit(String value, int offset, int limit);

}
