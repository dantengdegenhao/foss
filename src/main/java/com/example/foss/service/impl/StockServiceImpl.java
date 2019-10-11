package com.example.foss.service.impl;

import com.example.foss.mapper.StockDao;
import com.example.foss.pojo.Stock;
import com.example.foss.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/18
 * @description 库存
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDao stockDao;
    @Override
    public int add(Stock order) {
        return stockDao.insert(order);
    }

    @Override
    public int delete(Long id) {
        return stockDao.deleteById(id);
    }

    @Override
    public int update(Stock order) {
        return stockDao.updateById(order);
    }

    @Override
    public List<Stock> get() {
        return stockDao.selectList(null);
    }

    @Override
    /*@Transactional*/
    public List<Stock> get(Stock stock) throws Exception {
        return stockDao.get(stock);
    }
}
