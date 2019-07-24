package com.skp.casntemplt.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;


/*
 * @EnableCassandraRepositories(basePackages =
 * "com.skp.data.cassandra.repository")
 */
@Configuration
@EntityScan(basePackages = {"com.skp.casntmplt.model"})
public class CassandraConfig extends AbstractCassandraConfiguration{

	public static final String KEYSPACE = "soubhagya";

	@Override
	protected String getKeyspaceName() {
		return KEYSPACE;
	}
	
	@Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);

        return Arrays.asList(specification);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
    }

	

}
