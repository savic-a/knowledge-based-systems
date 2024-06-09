package com.ftn.sbnz.service.services.interfaces;

public interface ILoadDataService {
    
    public <T extends IService<E>, E> void loadData(T service);
}
