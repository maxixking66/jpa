package ir.maktab56.jpa.service.impl;

import ir.maktab56.jpa.base.service.impl.BaseEntityServiceImpl;
import ir.maktab56.jpa.domain.Wallet;
import ir.maktab56.jpa.repository.WalletRepository;
import ir.maktab56.jpa.service.WalletService;

public class WalletServiceImpl extends BaseEntityServiceImpl<Wallet, Long, WalletRepository>
        implements WalletService {

    public WalletServiceImpl(WalletRepository repository) {
        super(repository);
    }

}
