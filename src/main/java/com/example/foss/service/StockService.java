package com.example.foss.service;



import com.example.foss.pojo.Stock;

import java.util.List;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/18
 * @description 库存
 */
public interface StockService {
    public int add(Stock order)throws Exception;
    public int delete(Long id)throws Exception;
    public int update(Stock order)throws Exception;
    public List<Stock> get()throws Exception;
    public List<Stock> get(Stock stock)throws Exception;
}
