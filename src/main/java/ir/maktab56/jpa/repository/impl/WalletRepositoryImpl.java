package ir.maktab56.jpa.repository.impl;

import ir.maktab56.jpa.base.repository.BaseEntityRepositoryImpl;
import ir.maktab56.jpa.domain.Wallet;
import ir.maktab56.jpa.repository.WalletRepository;

import javax.persistence.EntityManager;

public class WalletRepositoryImpl extends BaseEntityRepositoryImpl<Wallet, Long> implements WalletRepository {

    public WalletRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Wallet> getEntityClass() {
        return Wallet.class;
    }
}
