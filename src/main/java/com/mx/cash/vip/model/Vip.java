package com.mx.cash.vip.model;

import com.mx.generator.pojo.CashVip;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Vip extends CashVip {

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 开户日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date openTime;

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public Date getOpenTime() {
        return openTime;
    }

    @Override
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }
}