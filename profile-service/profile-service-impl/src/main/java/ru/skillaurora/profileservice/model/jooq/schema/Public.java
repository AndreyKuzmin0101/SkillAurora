/*
 * This file is generated by jOOQ.
 */
package ru.skillaurora.profileservice.model.jooq.schema;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import ru.skillaurora.profileservice.model.jooq.schema.tables.Country;
import ru.skillaurora.profileservice.model.jooq.schema.tables.ProfileInfo;
import ru.skillaurora.profileservice.model.jooq.schema.tables.Skill;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * Словарь стран
     */
    public final Country COUNTRY_ENTITY = Country.COUNTRY_ENTITY;

    /**
     * Таблица данных профиля пользователя
     */
    public final ProfileInfo PROFILE_INFO_ENTITY = ProfileInfo.PROFILE_INFO_ENTITY;

    /**
     * Таблица навыков пользователя
     */
    public final Skill SKILL_ENTITY = Skill.SKILL_ENTITY;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Country.COUNTRY_ENTITY,
            ProfileInfo.PROFILE_INFO_ENTITY,
            Skill.SKILL_ENTITY
        );
    }
}