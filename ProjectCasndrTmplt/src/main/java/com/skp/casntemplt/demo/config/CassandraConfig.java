
package com.skp.casntemplt.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;

@Configuration
@EntityScan(basePackages = { "com.skp.casntmplt.demo.model" })
public class CassandraConfig extends AbstractCassandraConfiguration {

	public static final String KEYSPACE = "soubhagya";

	@Override
	protected String getKeyspaceName() {
		return KEYSPACE;
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.NONE;
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "com.skp.casntemplt.models" };
	}

	/*
	 * // @Override protected List<CreateKeyspaceSpecification>
	 * getKeyspaceCreations() { CreateKeyspaceSpecification specification =
	 * CreateKeyspaceSpecification.createKeyspace(KEYSPACE);
	 * 
	 * return Arrays.asList(specification); }
	 * 
	 * @Override protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
	 * return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE)); }
	 */

}
