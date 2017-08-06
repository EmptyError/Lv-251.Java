package com.softserve.edu.lv251.config;


import com.softserve.edu.lv251.dto.pojos.*;
import com.softserve.edu.lv251.entity.Clinics;

import com.softserve.edu.lv251.entity.Doctors;

import com.softserve.edu.lv251.entity.Contacts;

import com.softserve.edu.lv251.entity.Users;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper extends ConfigurableMapper{

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(UserDTO.class, Users.class)
                .field("firstName", "firstname")
                .field("lastName", "lastname")
                .field("password", "password")
                .field("email", "email")
                .exclude("matchingPassword")
                .byDefault().register();

        factory.classMap(PersonalInfoDTO.class, Users.class)
                .field("firstname", "firstname")
                .field("lastname", "lastname")
                .field("email", "email")
                .byDefault().register();



        factory.classMap(PersonalInfoDTO.class, Contacts.class)
                .field("address", "address")
                .field("city", "city")
                .field("zipCode", "zipCode")
                .field("firstPhone", "firstPhone")
                .field("secondPhone", "secondPhone")
                .field("thirdPhone", "thirdPhone")
                .byDefault().register();


        factory.classMap(ClinicInfoDTO.class,Clinics.class)
                .field("clinic_name","clinic_name")
                .field("description","description")
                .byDefault().register();

         factory.classMap(ClinicInfoDTO.class,Contacts.class)
                 .field("address", "address")
                 .field("city", "city")
                 .field("zipCode", "zipCode")
                 .field("firstPhone", "firstPhone")
                 .field("secondPhone", "secondPhone")
                 .field("thirdPhone", "thirdPhone")
                 .byDefault().register();

        factory.classMap(ClinicLatLngDTO.class, Clinics.class)
                .customize(new CustomMapper<ClinicLatLngDTO, Clinics>() {
                    @Override
                    public void mapAtoB(ClinicLatLngDTO latLng, Clinics clinics, MappingContext context) {
                        double lat = clinics.getContact().getLatitude();
                        double lng = clinics.getContact().getLongitude();
                        latLng.setLat(lat);
                        latLng.setLng(lng);
                        latLng.setId(clinics.getId());
                    }
                }).register();
        factory.classMap(PatientDTO.class, Users.class)
                .customize(new CustomMapper<PatientDTO, Users>() {
                    @Override
                    public void mapAtoB(PatientDTO patientDTO, Users users, MappingContext context) {
                        patientDTO.setId(users.getId());
                        String fullName = users.getLastname() + " "
                                + users.getFirstname() + " "
                                + users.getLastname();
                        patientDTO.setFullName(fullName);
                    }
                }).register();
        factory.classMap(SearchResultDoctorDTO.class, Doctors.class).customize(new CustomMapper<SearchResultDoctorDTO, Doctors>() {
            @Override
            public void mapAtoB(SearchResultDoctorDTO searchResultDoctorDTO, Doctors doctor, MappingContext context) {
                super.mapAtoB(searchResultDoctorDTO, doctor, context);
                searchResultDoctorDTO.setId(doctor.getId());
                searchResultDoctorDTO.setDescription(doctor.getDescription());
                searchResultDoctorDTO.setFirstName(doctor.getFirstname());
                searchResultDoctorDTO.setLastName(doctor.getLastname());
                searchResultDoctorDTO.setMiddleName(doctor.getMiddlename());
                searchResultDoctorDTO.setSpecialisation(doctor.getSpecialization().getName());
                searchResultDoctorDTO.setPhoto(doctor.getPhoto());

                ContactsDTO contacts = new ContactsDTO();
                contacts.setAddress(doctor.getContact().getAddress());
                contacts.setLatitude(doctor.getContact().getLatitude());
                contacts.setLongitude(doctor.getContact().getLongitude());
                contacts.setCity(doctor.getContact().getCity());
                contacts.setDistrict(doctor.getContact().getDistrict().getName());
                contacts.setEmail(doctor.getContact().getEmail());
                List<String> phones = new ArrayList<>();
                phones.add(doctor.getContact().getFirstPhone());
                phones.add(doctor.getContact().getSecondPhone());
                phones.add(doctor.getContact().getThirdPhone());
                contacts.setPhones(phones);
                searchResultDoctorDTO.setContacts(contacts);

                searchResultDoctorDTO.setClinicId(doctor.getClinics().getId());
                searchResultDoctorDTO.setClinicName(doctor.getClinics().getClinic_name());

            }
        });

        factory.classMap(Clinics.class, SearchResultClinicDTO.class).customize(new CustomMapper<Clinics, SearchResultClinicDTO>() {
            @Override
            public void mapAtoB(Clinics clinic, SearchResultClinicDTO searchResultClinicDTO, MappingContext context) {


                searchResultClinicDTO.setId(clinic.getId());
                searchResultClinicDTO.setName(clinic.getClinic_name());
                searchResultClinicDTO.setDescription(clinic.getDescription());
                searchResultClinicDTO.setPhoto(clinic.getPhoto());

                ContactsDTO contacts = new ContactsDTO();
                contacts.setAddress(clinic.getContact().getAddress());
                contacts.setLatitude(clinic.getContact().getLatitude());
                contacts.setLongitude(clinic.getContact().getLongitude());
                contacts.setCity(clinic.getContact().getCity());
                contacts.setDistrict(clinic.getContact().getDistrict().getName());
                contacts.setEmail(clinic.getContact().getEmail());
                List<String> phones = new ArrayList<>();
                phones.add(clinic.getContact().getFirstPhone());
                phones.add(clinic.getContact().getSecondPhone());
                phones.add(clinic.getContact().getThirdPhone());
                contacts.setPhones(phones);
                searchResultClinicDTO.setContacts(contacts);

            }
        }).register();
}
    }
