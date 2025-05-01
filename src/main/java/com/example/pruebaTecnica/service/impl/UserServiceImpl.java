package com.example.pruebaTecnica.service.impl;

import com.example.pruebaTecnica.exception.ResourceNotFoundException;
import com.example.pruebaTecnica.model.User;
import com.example.pruebaTecnica.repository.IUserRepository;
import com.example.pruebaTecnica.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class for service of table User
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository iUserRepository;

    /**List all Users*/
    @Override
    public List<User> findAll() {
        return this.iUserRepository.findAll();
    }

    /**Create a new User*/
    @Override
    @Transactional
    public User createUser(User aUser) {
        return this.iUserRepository.save(aUser);
    }

    /**Delete a User*/
    @Override
    @Transactional
    public void updateUser(User aUser) {
        var userBD = this.iUserRepository.findById(aUser.getId()).orElseThrow(() -> new ResourceNotFoundException("No existe este Usuario" ));
        this.copiarPropiedadesObjetoAHaciaBIgnorandoNulosDeA(aUser, userBD);
        userBD.setIdentification(aUser.getIdentification());
        userBD.setName(aUser.getName());
        userBD.setTelephone(aUser.getTelephone());
        aUser = userBD;
        this.iUserRepository.save(aUser);
    }

    /**Delete a User*/
    @Override
    @Transactional
    public void deleteUser(Long aId) {
        var userBD = this.iUserRepository.findById(aId).orElseThrow(() -> new ResourceNotFoundException("No existe un Usuario con id: " +aId));
        this.iUserRepository.deleteById(userBD.getId());
    }

    /**
     * One by one, the properties from the source to the destination are set, ignoring the fields null
     *
     * @param aOrigenA
     * @param aDestinoB
     * @return aDestinoB
     */
    public static Object copiarPropiedadesObjetoAHaciaBIgnorandoNulosDeA(Object aOrigenA, Object aDestinoB) {
        String[] ignoredAttributes = getAttributesNull(aOrigenA);
        BeanUtils.copyProperties(aOrigenA, aDestinoB, ignoredAttributes);
        return aDestinoB;
    }

    /**
     * Gets the null fields of the object to be analyzed
     *
     * @param object
     * @return wrappedSource
     */
    private static String[] getAttributesNull(Object object) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(object);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
    }

    //Injections
    @Autowired
    public void setiUserRepository(
            IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }
}
