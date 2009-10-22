package br.com.dclick.dtomanager.fixtures;

import java.util.List;

import br.com.dclick.dtomanager.annotations.Composition;
import br.com.dclick.dtomanager.annotations.Ignore;
import br.com.dclick.dtomanager.annotations.Listing;

/**
 * 
 * @author bfuster
 * 
 */
public class ComplexFixture {

	private String value;

	@Ignore
	private String ignore;

	@Composition
	private NestedFixture nested;

	@Listing( NestedFixture.class )
	private List< NestedFixture > list;

	/* getters n' setters */

	public String getValue() {

		return value;
	}

	public void setValue( String value ) {

		this.value = value;
	}

	public String getIgnore() {

		return ignore;
	}

	public void setIgnore( String ignore ) {

		this.ignore = ignore;
	}

	public NestedFixture getNested() {

		return nested;
	}

	public void setNested( NestedFixture nested ) {

		this.nested = nested;
	}

	public List< NestedFixture > getList() {

		return list;
	}

	public void setList( List< NestedFixture > list ) {

		this.list = list;
	}

}
