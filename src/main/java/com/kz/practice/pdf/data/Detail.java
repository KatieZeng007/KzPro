package com.kz.practice.pdf.data;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 明细数据
 * @Author KatieZ
 * @Date Created in 15:42  15:42
 */
@Data
public class Detail implements Serializable {

    /**
     * 采购单号
     */
    private String purchaseNo;

    /**
     * 货号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * CAS
     */
    private String cas;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 型号规格98%
     */
    private String modelSpec;

    /**
     * 配送数量(一级单位)
     */
    private BigDecimal deliveryCount;

    /**
     * 配送数量(二级单位)
     */
    private BigDecimal deliverySecondCount;

    /**
     * 单价
     */
    private BigDecimal singlePrice;

    /**
     * 总价/金额
     */
    private BigDecimal amount;

    /**
     * 一级单位名称
     */
    private String stairUnitName;
    /**
     * 二级单位名称
     */
    private String secondUnitName;

    /**
     * 供应商简称
     */
    private String supplierShortName;

    /**
     * 库位名称
     */
    private String warehouseShelfName;
}
