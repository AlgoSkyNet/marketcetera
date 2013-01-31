package org.marketcetera.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.marketcetera.api.systemmodel.MutablePermission;
import org.marketcetera.api.systemmodel.Permission;
import org.marketcetera.dao.PermissionDao;
import org.marketcetera.dao.domain.PersistentPermission;

/**
 * @version $Id$
 * @date 6/29/12 12:38 AM
 */

public class PermissionDaoImpl implements PermissionDao {
    private EntityManager entityManager;


    @Override
    public void add(Permission inData) {
        entityManager.persist(inData);
    }

    @Override
    public void save(Permission inData) {
        entityManager.merge(inData);
    }

    @Override
    public PersistentPermission getByName(String inName) {
        return (PersistentPermission)entityManager.createNamedQuery("PersistentPermission.findByName").setParameter("name", inName).getSingleResult();
    }

    @Override
    public PersistentPermission getById(long inId) {
        return entityManager.find(PersistentPermission.class,
                                  inId); 
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<MutablePermission> getAll() {
        return entityManager.createNamedQuery("PersistentPermission.findAll").getResultList();
    }
    private void parsePermissions(List<Permission> permissions) {
        for(Permission permission : permissions) {
            // TODO a bit of hackery here, got to find a better way to invoke this (problem is that postLoad is not invoked with query)
            ((PersistentPermission)permission).calculateMethodSet();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getAllByUsername(String username) {
        List<Permission> permissions = (List<Permission>) entityManager.createNamedQuery("PersistentPermission.findAllByUsername").setParameter("name", username).getResultList();
        parsePermissions(permissions);
        return permissions;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.api.dao.PermissionDao#delete(org.marketcetera.core.systemmodel.Permission)
     */
    @Override
    public void delete(Permission inPermission)
    {
        entityManager.remove(entityManager.merge(inPermission));
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}