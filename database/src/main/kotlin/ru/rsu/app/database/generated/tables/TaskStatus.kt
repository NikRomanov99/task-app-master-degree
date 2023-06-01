/*
 * This file is generated by jOOQ.
 */
package ru.rsu.app.database.generated.tables


import java.util.function.Function

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Name
import org.jooq.Record
import org.jooq.Records
import org.jooq.Row3
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

import ru.rsu.app.database.generated.Public
import ru.rsu.app.database.generated.keys.TASK_STATUS_PKEY
import ru.rsu.app.database.generated.tables.records.TaskStatusRecord


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class TaskStatus(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, TaskStatusRecord>?,
    aliased: Table<TaskStatusRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<TaskStatusRecord>(
    alias,
    Public.PUBLIC,
    child,
    path,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table()
) {
    companion object {

        /**
         * The reference instance of <code>public.task_status</code>
         */
        val TASK_STATUS: TaskStatus = TaskStatus()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<TaskStatusRecord> = TaskStatusRecord::class.java

    /**
     * The column <code>public.task_status.id</code>.
     */
    val ID: TableField<TaskStatusRecord, Long?> = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.task_status.name</code>.
     */
    val NAME: TableField<TaskStatusRecord, String?> = createField(DSL.name("name"), SQLDataType.CLOB.nullable(false), this, "")

    /**
     * The column <code>public.task_status.description</code>.
     */
    val DESCRIPTION: TableField<TaskStatusRecord, String?> = createField(DSL.name("description"), SQLDataType.CLOB, this, "")

    private constructor(alias: Name, aliased: Table<TaskStatusRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<TaskStatusRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>public.task_status</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.task_status</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.task_status</code> table reference
     */
    constructor(): this(DSL.name("task_status"), null)

    constructor(child: Table<out Record>, key: ForeignKey<out Record, TaskStatusRecord>): this(Internal.createPathAlias(child, key), child, key, TASK_STATUS, null)
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIdentity(): Identity<TaskStatusRecord, Long?> = super.getIdentity() as Identity<TaskStatusRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<TaskStatusRecord> = TASK_STATUS_PKEY
    override fun `as`(alias: String): TaskStatus = TaskStatus(DSL.name(alias), this)
    override fun `as`(alias: Name): TaskStatus = TaskStatus(alias, this)
    override fun `as`(alias: Table<*>): TaskStatus = TaskStatus(alias.getQualifiedName(), this)

    /**
     * Rename this table
     */
    override fun rename(name: String): TaskStatus = TaskStatus(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): TaskStatus = TaskStatus(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): TaskStatus = TaskStatus(name.getQualifiedName(), null)

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row3<Long?, String?, String?> = super.fieldsRow() as Row3<Long?, String?, String?>

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    fun <U> mapping(from: (Long?, String?, String?) -> U): SelectField<U> = convertFrom(Records.mapping(from))

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    fun <U> mapping(toType: Class<U>, from: (Long?, String?, String?) -> U): SelectField<U> = convertFrom(toType, Records.mapping(from))
}
