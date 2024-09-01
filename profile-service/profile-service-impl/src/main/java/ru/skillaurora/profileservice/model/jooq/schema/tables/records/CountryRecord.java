/*
 * This file is generated by jOOQ.
 */
package ru.skillaurora.profileservice.model.jooq.schema.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import ru.skillaurora.profileservice.model.jooq.schema.tables.Country;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.CountryEntity;


/**
 * Словарь стран
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CountryRecord extends UpdatableRecordImpl<CountryRecord> implements Record3<Short, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.country.id</code>.
     */
    public void setId(Short value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.country.id</code>.
     */
    public Short getId() {
        return (Short) get(0);
    }

    /**
     * Setter for <code>public.country.code</code>.
     */
    public void setCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.country.code</code>.
     */
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.country.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.country.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Short> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Short, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Short, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Short> field1() {
        return Country.COUNTRY_ENTITY.ID;
    }

    @Override
    public Field<String> field2() {
        return Country.COUNTRY_ENTITY.CODE;
    }

    @Override
    public Field<String> field3() {
        return Country.COUNTRY_ENTITY.NAME;
    }

    @Override
    public Short component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getCode();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public Short value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getCode();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public CountryRecord value1(Short value) {
        setId(value);
        return this;
    }

    @Override
    public CountryRecord value2(String value) {
        setCode(value);
        return this;
    }

    @Override
    public CountryRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public CountryRecord values(Short value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CountryRecord
     */
    public CountryRecord() {
        super(Country.COUNTRY_ENTITY);
    }

    /**
     * Create a detached, initialised CountryRecord
     */
    public CountryRecord(Short id, String code, String name) {
        super(Country.COUNTRY_ENTITY);

        setId(id);
        setCode(code);
        setName(name);
    }

    /**
     * Create a detached, initialised CountryRecord
     */
    public CountryRecord(CountryEntity value) {
        super(Country.COUNTRY_ENTITY);

        if (value != null) {
            setId(value.getId());
            setCode(value.getCode());
            setName(value.getName());
        }
    }
}
